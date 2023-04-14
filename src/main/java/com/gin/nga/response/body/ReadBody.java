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
import com.gin.nga.utils.NgaLink;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.LinkedHashMap;
import java.util.Map;
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
     * 正则，用于从网页中解析当前主题的总页数,当前页
     */
    public static final  Pattern PAGER_PATTERN = Pattern.compile("1:(\\d+),2:(\\d+),3:(\\d+)")  ;
    /**
     * 正则，用于从网页中解析用户信息
     */
    public static final  Pattern USER_INFO_PATTERN = Pattern.compile("commonui\\.userInfo\\.setAll\\((.+?)\\)")  ;
    /**
     * 正则，用于从网页中解析声望等级信息
     */
    public static final  Pattern CUSTOM_LEVEL_PATTERN = Pattern.compile("__CUSTOM_LEVEL=(.+)")  ;

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
                    final LinkedHashMap<String, Object> map = JacksonUtils.MAPPER.readValue(matcher.group(1), mapLikeType);
                    this.userInfoField = new UserFieldInRead(map);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
        //todo 主题信息

        //todo 回复信息
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
