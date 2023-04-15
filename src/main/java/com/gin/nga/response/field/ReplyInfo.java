package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ZdtJsonSerializer;
import com.gin.common.utils.TimeUtils;
import com.gin.nga.deserializer.GiftDeserializer;
import com.gin.nga.enums.FromClient;
import com.gin.nga.utils.HtmlUtils;
import com.gin.nga.utils.QueryStringUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 回复信息(read.php接口)
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 11:46
 */
@Getter
@Setter
@NoArgsConstructor
public class ReplyInfo extends ReplySimple {
    /**
     * 正则，用于从网页中解析 修改记录
     */
    public static final Pattern ALTER_INFO_PATTERN = Pattern.compile("loadAlertInfo\\('(.+?)'");
    /**
     * 正则，用于从网页中解析 贴条
     */
    public static final Pattern COMMENT_PATTERN = Pattern.compile("评论</h4>(.+?)<div class=\"clear\">");
    /**
     * 正则，用于从网页中解析 热评
     */
    public static final Pattern HOT_REPLY_PATTERN = Pattern.compile("热点回复</h4>(.+?)<div class=\"clear\">");
    /**
     * 正则，用于script数据的预处理
     */
    public static final Pattern SCRIPT_PATTERN = Pattern.compile("'(.*?)'");

    /**
     * script数据中用于替换参数内逗号的分隔符
     */
    public static final String SP = "-";

    /**
     * 礼物，key =id value = 数量
     * 地址前缀为:  https://img4.nga.178.com/ngabbs/nga_classic/items/5_
     */
    @JsonProperty("14")
    @JsonDeserialize(using = GiftDeserializer.class)
    List<Gift> gifts;
    /**
     * 修改记录, 包括编辑、加分、处罚、撤销处罚 ,
     */
    @JsonProperty("alterinfo")
    AlterInfo alterInfo;
    /**
     * 附件信息
     */
    @JsonProperty("attachs")
    LinkedHashMap<Integer, Attachment> attachments;
    /**
     * 热评
     */
    @JsonProperty("hotreply")
    LinkedHashMap<Integer, ReplyInfo> hotReplies;
    /**
     * 贴条
     */
    @JsonProperty("comment")
    LinkedHashMap<Integer, ReplyInfo> comment;
    /**
     * 使用的客户端,PC,IOS,安卓
     */
    @JsonProperty("from_client")
    FromClient fromClient;
    /**
     * 楼层号
     */
    @JsonProperty("lou")
    Integer floorNumber;
    /**
     * 回复id
     */
    @JsonProperty("pid")
    Long replyId;
    /**
     * 发表日期
     */
    @JsonProperty("postdate")
    String postDate;
    /**
     * 发表日期
     */
    @JsonProperty("postdatetimestamp")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime postDatetime;
    /**
     * 推荐分
     */
    @JsonProperty("recommend")
    Integer recommendScore;
    /**
     * 赞数
     */
    @JsonProperty("score")
    Integer agreeCount;
    /**
     * 踩数
     */
    @JsonProperty("score_2")
    Integer disagreeCount;


    /**
     * 从网页解析回复信息
     * @param root 网页标签
     */
    public ReplyInfo(int index, Element root, Long topicId) {
        this.topicId = topicId;
        //todo
        final String rootString = HtmlUtils.clearLinkBreak(root.toString());
//        System.out.println(rootString);
        // 作者uid
        handleElement(root, "postauthor" + index, e -> this.authorUid = parseUidFromA(e));

        // 发表日期 发表时间戳
        handleElement(root, "postdate" + index, e -> {
            this.postDate = e.ownText();
            this.postDatetime = TimeUtils.parse(e.ownText(), TimeUtils.CHINESE_ZONE_ID);
        });
        //标题
        handleElement(root, "postsubject" + index, e -> this.title = e.ownText());

        // 回复正文
        handleElement(root, "postcontent" + index, e -> this.content = handleContent(e));

        //改动信息
        {
            final Matcher matcher = ALTER_INFO_PATTERN.matcher(rootString);
            if (matcher.find()) {
                final String s = matcher.group(1).replace("\t", "");
                this.alterInfo = new AlterInfo(s);
            }
        }

        // 贴条
        {
            final Matcher matcher = COMMENT_PATTERN.matcher(rootString);
            if (matcher.find()) {
                final String group = matcher.group(1);
                this.comment = parseComment(group);
                this.comment.forEach((k,v)->v.setTopicId(this.topicId));
            }
        }
        // 热评
        {
            final Matcher matcher = HOT_REPLY_PATTERN.matcher(rootString);
            if (matcher.find()) {
                final String group = matcher.group(1);
                this.hotReplies = parseComment(group);
                this.hotReplies.forEach((k,v)->v.setTopicId(this.topicId));
            }
        }
        //todo 客户端
        {
            handleElement(root, "postInfo" + index, e -> {
                // a标签
                final Elements elements = e.getElementsByTag("a");


            });
        }
        //todo 附件信息
        //todo 楼层号
        //todo 回复pid
        //todo 赞数
        //todo 回复id
        //todo 回复或引用的id
        //todo 主题id
        //todo 类型

    }

    /**
     * 从root元素中以id查找指定元素，如果发现，执行handle
     * @param root   root元素
     * @param id     id
     * @param handle 处理方法
     */
    private static void handleElement(Element root, String id, Handle handle) {
        final Element e = root.getElementById(id);
        if (e != null) {
            handle.handle(e);
        }
    }

    /**
     * 解析评论贴条和热评
     * @param html html
     * @return 评论贴条和热评
     */
    private static LinkedHashMap<Integer, ReplyInfo> parseComment(String html) {
        final Document root = Jsoup.parse(html);
        final LinkedHashMap<Integer, ReplyInfo> res = new LinkedHashMap<>();

        final Elements comments = root.getElementsByClass("comment_c left");
        for (int i = 0; i < comments.size(); i++) {
            final Element comment = comments.get(i);
//            System.out.println("comment = " + comment);
            final Element a = comment.select(".posterinfo > a").first();
            if (a != null) {
                final ReplyInfo replyInfo = new ReplyInfo();
                res.put(i, replyInfo);

                // 作者uid
                replyInfo.setAuthorUid(parseUidFromA(a));
                // 回复id
                final String[] idSplit = a.id().split("_");
                final String replyId = idSplit[idSplit.length - 1];
                replyInfo.setReplyId(Long.valueOf(replyId));

                // 回复时间
                handleElement(comment, "commentInfo_" + replyId, e ->
                        e.getElementsByTag("span").stream()
                                .filter(it -> "reply time".equals(it.attr("title")))
                                .findFirst().ifPresent(it -> replyInfo.setPostDatetime(TimeUtils.parse(it.ownText(), TimeUtils.CHINESE_ZONE_ID))));
                handleElement(comment, "commentInfo__" + replyId, e ->
                        e.getElementsByTag("span").stream()
                                .filter(it -> "reply time".equals(it.attr("title")))
                                .findFirst().ifPresent(it -> replyInfo.setPostDatetime(TimeUtils.parse(it.ownText(), TimeUtils.CHINESE_ZONE_ID))));
                // todo 改动信息

                // 标题
                handleElement(comment, "postcommentsubject_" + replyId, e -> replyInfo.setTitle(e.ownText()));
                handleElement(comment, "postcommentsubject__" + replyId, e -> replyInfo.setTitle(e.ownText()));
                // 正文
                handleElement(comment, "postcomment_" + replyId, e -> replyInfo.setContent(handleContent(e)));
                handleElement(comment, "postcomment__" + replyId, e -> replyInfo.setContent(handleContent(e)));
            }
        }

        return res;
    }

    /**
     * 从 a 标签中解析uid
     * @param e a标签
     * @return uid
     */
    private static Long parseUidFromA(Element e) {
        final String href = e.attr("href");
        final HashMap<String, Object> param = QueryStringUtils.parse(href.substring(href.indexOf("?") + 1));
        final Object uid = param.get("uid");
        return uid == null ? null : Long.valueOf(String.valueOf(uid));
    }

    /**
     * 解析script标签中的数据
     * @param script 参数列表
     */
    public void parseScript(String script) {
        // 预处理
        List<String> rawList = new ArrayList<>();
        final Matcher matcher = SCRIPT_PATTERN.matcher(script);
        while (matcher.find()) {
            final String group = matcher.group(1);
            if (group.contains(",")) {
                rawList.add(group);
            }
        }
        if (rawList.size() > 0) {
            for (String raw : rawList) {
                script = script.replace(raw, raw.replace(",", SP));
            }
        }

        final List<String> params = Arrays.stream(script.split(","))
                .filter(i -> !i.startsWith("$"))
                .map(i -> {
                    if ("null".equals(i) || "".equals(i) || "''".equals(i)) {
                        return null;
                    }
                    return i.replace("'", "").trim();
                })
                .toList();
//        System.out.println("params = " + params);

        // 楼层
        this.floorNumber = Integer.valueOf(params.get(0));
        // 回复id
        this.replyId = Long.valueOf(params.get(3));
        this.type = Long.valueOf(params.get(4));
        this.authorUid = Long.valueOf(params.get(6));
        this.postDatetime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(params.get(7))), ZoneId.systemDefault());
        {
            final String[] split = params.get(8).split(SP);
            // 推荐分
            this.recommendScore = Integer.valueOf(split[0]);
            // 赞踩数
            this.agreeCount = Integer.valueOf(split[1]);
            this.disagreeCount = Integer.valueOf(split[2]);
        }

        // 客户端
        this.fromClient = FromClient.findByValue(params.get(12));

        // 礼物
        final String gift = params.get(15);
        if (!"0".equals(gift)) {
            this.gifts = new ArrayList<>();
            final String[] a = gift.split(SP);
            for (int i = 0; i < a.length; i += 2) {
                gifts.add(new Gift(Integer.parseInt(a[0]), Integer.parseInt(a[1])));
            }
        }

    }

    private interface Handle {
        /**
         * 处理元素
         * @param e 元素
         */
        void handle(Element e);
    }

    /**
     * 处理正文(换行符问题)
     * @param e 正文标签
     * @return 正文内容
     */
    private static String handleContent(Element e){
        final String replacement = "{换行}";
        return Jsoup.parse(HtmlUtils.clearLinkBreak(e.toString()).replace("<br/>", replacement).replace("<br>", replacement))
                .text().replace(replacement, "<br/>");
    }

}
