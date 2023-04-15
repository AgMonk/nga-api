package com.gin.nga.response.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gin.common.utils.JacksonUtils;
import com.gin.nga.deserializer.CustomLevelDeserializer;
import com.gin.nga.document.DocLink;
import com.gin.nga.document.Navigation;
import com.gin.nga.enums.NgaLinkType;
import com.gin.nga.response.field.*;
import com.gin.nga.utils.HtmlUtils;
import com.gin.nga.utils.NgaLink;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * read.php 的响应data
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 10:40
 */
@Getter
@Setter
@NoArgsConstructor
public class ReadBody {
    /**
     * 正则，用于从网页中解析声望等级信息
     */
    public static final Pattern CUSTOM_LEVEL_PATTERN = Pattern.compile("__CUSTOM_LEVEL=(.+)");
    /**
     * 正则，用于从网页中解析当前主题的总页数,当前页
     */
    public static final Pattern PAGER_PATTERN = Pattern.compile("1:(\\d+),2:(\\d+),3:(\\d+)");
    /**
     * 正则，用于从网页中解析客户端信息
     */
    public static final Pattern POST_ARG_PATTERN = Pattern.compile("commonui.postArg.proc\\((.+)\\)if");
    /**
     * 正则，用于从网页中解析 主题id
     */
    public static final Pattern TOPIC_ID_PATTERN = Pattern.compile("__CURRENT_TID=(\\d+)");
    /**
     * 正则，用于从网页中解析用户信息
     */
    public static final Pattern USER_INFO_PATTERN = Pattern.compile("commonui\\.userInfo\\.setAll\\((.+)");
    /**
     * 是否为兼容模式, 即，数据从网页中解析获得
     */
    boolean document = false;

    @JsonProperty("__CU")
    CurrentUser currentUser;

    @JsonProperty("__F")
    Forum forum;
    /**
     * 当前页码
     */
    @JsonProperty("__PAGE")
    Integer page = 1;
    /**
     * 楼层总数(含主楼)
     */
    @JsonProperty("__ROWS")
    Integer total;
    /**
     * 总页数
     */
    @JsonProperty("__PAGE_TOTAL")
    Integer totalPage;
    /**
     * 每页回复数
     */
    @JsonProperty("__R__ROWS_PAGE")
    Integer size = 20;
    /**
     * 主题信息
     */
    @JsonProperty("__T")
    TopicInfoInRead topicInfo;
    /**
     * 用户相关信息
     */
    @JsonProperty("__U")
    UserFieldInRead userInfoField;
    /**
     * 回复信息
     */
    @JsonProperty("__R")
    Map<Integer, ReplyInfo> replies;

    public ReadBody(Document document) {
        final String docString = document.toString();
        this.document = true;
        // 导航栏
        final Element nav = document.getElementsByClass("nav").first();
        assert nav != null;
        final Navigation navigation = new Navigation(nav);

        //版面信息
        final DocLink forumLink = navigation.findLast(NgaLinkType.FORUM);
        if (forumLink != null) {
            final NgaLink link = forumLink.getLink();

            this.forum = new Forum();
            this.forum.setForumName(forumLink.getTitle());
            this.forum.setForumId(link.findId(NgaLinkType.FORUM.field));

            final DocLink colLink = navigation.findLast(NgaLinkType.COL);
            if (colLink != null) {
                this.forum.setColTid(colLink.getLink().findId(NgaLinkType.COL.field));
                this.forum.setColTitle(colLink.getTitle());
            }

            final Matcher matcher = CUSTOM_LEVEL_PATTERN.matcher(docString);
            if (matcher.find()) {
                try {
                    this.forum.setCustomLevels(CustomLevelDeserializer.parse(matcher.group(1)));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }

        //解析 当前页码和总页数

        // 顶部翻页器
        final Element pager = document.getElementById("pagebtop");
        this.totalPage = 1;
        if (pager != null) {
            final Matcher matcher = PAGER_PATTERN.matcher(pager.toString());
            if (matcher.find()) {
                this.totalPage = Integer.valueOf(matcher.group(1));
                this.page = Integer.valueOf(matcher.group(2));
                this.size = Integer.valueOf(matcher.group(3));
            }
        }

        //用户相关信息
        {
            final Matcher matcher = USER_INFO_PATTERN.matcher(docString);
            if (matcher.find()) {
                try {
                    final TypeFactory typeFactory = JacksonUtils.MAPPER.getTypeFactory();
                    final MapLikeType mapLikeType = typeFactory.constructMapLikeType(LinkedHashMap.class, String.class, Object.class);
                    final String group = matcher.group(1);
                    final LinkedHashMap<String, Object> map = JacksonUtils.MAPPER.readValue(group.substring(0, group.length() - 2), mapLikeType);
                    this.userInfoField = new UserFieldInRead(map);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
        // 主题信息
        this.topicInfo = new TopicInfoInRead();
        {
            // 主题id
            final Matcher matcher = TOPIC_ID_PATTERN.matcher(docString);
            if (matcher.find()) {
                this.topicInfo.setTopicId(Long.valueOf(matcher.group(1)));
            }
            // 版面id
            this.topicInfo.setForumId(this.forum.getForumId());
            final DocLink topicLink = navigation.findLast(NgaLinkType.TOPIC);
            if (topicLink != null) {
                this.topicInfo.setTitle(topicLink.getTitle());
            }
        }

        //  回复信息
        this.replies = new LinkedHashMap<>();
        final Elements replyTables = document.getElementsByClass("forumbox postbox");
        for (int i = 0; i < replyTables.size(); i++) {
            // 回复的table 标签
            final Element replyTable = replyTables.get(i);
            // 解析回复数据
            final ReplyInfo replyInfo = new ReplyInfo();
            replyInfo.setTopicId(this.topicInfo.getTopicId());
            // table标签后跟随的 script 标签
            final String script = HtmlUtils.clearLinkBreak(Objects.requireNonNull(replyTable.nextElementSibling()).toString());
            final Matcher matcher = POST_ARG_PATTERN.matcher(script);
            if (matcher.find()) {
                // 解析 script 中的数据
                replyInfo.parseScript(matcher.group(1));
            }
            // 解析 DOM 数据
            replyInfo.parseElement(replyTable);

            this.replies.put(i, replyInfo);
        }
    }

    public Integer getTotalPage() {
        if (this.totalPage != null) {
            return this.totalPage;
        }
        final int p = this.total / this.size;
        final int m = this.total % this.size;
        return p + (m > 0 ? 1 : 0);
    }
}
