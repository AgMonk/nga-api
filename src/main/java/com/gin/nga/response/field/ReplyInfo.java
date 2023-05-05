package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ZdtJsonSerializer;
import com.gin.nga.deserializer.GiftDeserializer;
import com.gin.nga.enums.FromClient;
import com.gin.nga.response.NgaRes;
import com.gin.nga.utils.HtmlUtils;
import com.gin.nga.utils.QueryStringUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jsoup.Jsoup;
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
    private static final Pattern ALTER_INFO_PATTERN = Pattern.compile("loadAlertInfo\\('(.+?)'");
    /**
     * 正则，用于从网页中解析 附件信息
     */
    private static final Pattern ATTACH_PATTERN = Pattern.compile("attach\\.load.+?(\\[.+?])");
    /**
     * 正则，用于从网页中解析 贴条
     */
    private static final Pattern COMMENT_PATTERN = Pattern.compile("评论</h4>(.+?)<div class=\"clear\">");
    /**
     * 正则，用于从网页中解析 热评
     */
    private static final Pattern HOT_REPLY_PATTERN = Pattern.compile("热点回复</h4>(.+?)<div class=\"clear\">");
    /**
     * 正则，用于从网页中解析 回复或引用的目标
     */
    private static final Pattern REPLY_TO_PATTERN = Pattern.compile("pid=(\\d+),\\d+,\\d+");
    /**
     * 正则，用于script数据的预处理
     */
    private static final Pattern SCRIPT_PATTERN = Pattern.compile("'(.*?)'");
    /**
     * script数据中用于替换参数内逗号的分隔符
     */
    private static final String SP = "-";

    /**
     * 礼物，key =id value = 数量
     * 地址前缀为:  https://img4.nga.178.com/ngabbs/nga_classic/items/5_
     */
    @JsonAlias("14")
    @JsonDeserialize(using = GiftDeserializer.class)
    List<Gift> gifts;
    /**
     * 修改记录, 包括编辑、加分、处罚、撤销处罚 ,
     */
    @JsonAlias("alterinfo")
    AlterInfo alterInfo;
    /**
     * 附件信息
     */
    @JsonAlias("attachs")
    LinkedHashMap<Integer, Attachment> attachments;
    /**
     * 热评
     */
    @JsonAlias("hotreply")
    LinkedHashMap<Integer, ReplyInfo> hotReplies;
    /**
     * 贴条
     */
    @JsonAlias("comment")
    LinkedHashMap<Integer, ReplyInfo> comment;
    /**
     * 使用的客户端,PC,IOS,安卓
     */
    @JsonAlias("from_client")
    FromClient fromClient;
    /**
     * 楼层号
     */
    @JsonAlias("lou")
    Integer floorNumber;
    /**
     * 发表日期
     */
    @JsonAlias("postdate")
    String postDate;
    /**
     * 发表日期
     */
    @JsonAlias("postdatetimestamp")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime postDatetime;
    /**
     * 推荐分
     */
    @JsonAlias("recommend")
    Integer recommendScore;
    /**
     * 赞数
     */
    @JsonAlias("score")
    Integer agreeCount;
    /**
     * 踩数
     */
    @JsonAlias("score_2")
    Integer disagreeCount;

    /**
     * 处理正文(换行符问题)
     * @param e 正文标签
     * @return 正文内容
     */
    private static String handleContent(Element e) {
        final String replacement = "{换行}";
        return Jsoup.parse(
                        HtmlUtils.clearLinkBreak(e.toString())
                                .replace("<br/>", replacement)
                                .replace("<br>", replacement))
                .text().replace(replacement, "<br/>");
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
     * @param root 根元素
     * @return 评论贴条和热评
     */
    private static LinkedHashMap<Integer, ReplyInfo> parseComment(Element root) {
        final LinkedHashMap<Integer, ReplyInfo> res = new LinkedHashMap<>();

        final Elements comments = root.getElementsByClass("comment_c left");
        for (int i = 0; i < comments.size(); i++) {
            final Element comment = comments.get(i);
            final ReplyInfo replyInfo = new ReplyInfo();
            res.put(i, replyInfo);
//            System.out.println("comment = " + comment);
            final Element a = comment.select(".posterinfo > a").first();
            if (a != null) {
                // 作者uid
                replyInfo.setAuthorUid(parseUidFromA(a));
                // 回复id
                final String[] idSplit = a.id().split("_");
                final String replyId = idSplit[idSplit.length - 1];
                replyInfo.setReplyId(Long.valueOf(replyId));

                // 回复时间
                final Element replyTimeSpan = comment.select("[title='reply time']").first();
                if (replyTimeSpan != null) {
                    replyInfo.setPostDate(replyTimeSpan.ownText());
                }
                // 标题
                handleElement(comment, "postcommentsubject_" + replyId, e -> replyInfo.setTitle(e.ownText()));
                handleElement(comment, "postcommentsubject__" + replyId, e -> replyInfo.setTitle(e.ownText()));
                // 正文
                handleElement(comment, "postcomment_" + replyId, e -> replyInfo.setContent(handleContent(e)));
                handleElement(comment, "postcomment__" + replyId, e -> replyInfo.setContent(handleContent(e)));
            }

            final Element table = comment.getElementsByTag("table").first();
            if (table != null) {
                final Element script = table.nextElementSibling();
                if (script != null) {
                    final String s = HtmlUtils.clearLinkBreak(script.toString());
                    final String args = preHandle(s.substring(s.indexOf("(") + 1, s.lastIndexOf(")")), SP);
                    // 热评和贴条的数据
                    final List<String> list = Arrays.stream(args.split(","))
                            .map(item -> item.replace("'", "").trim())
                            .filter(item -> !item.startsWith("_") && !item.startsWith("$"))
                            .map(item -> {
                                if ("null".equals(item) || "".equals(item)) {
                                    return null;
                                }
                                return item;
                            })
                            .toList();
                    System.out.println("list = " + list);
                    // 类型、状态
                    replyInfo.setType(Integer.valueOf(list.get(5)));
                    // 时间戳
                    replyInfo.setPostDatetime(ZonedDateTime.ofInstant(Instant.ofEpochSecond(Long.parseLong(list.get(8))), ZoneId.systemDefault()));
                    // 推荐分 赞踩
                    {
                        final String[] split = list.get(9).split(SP);
                        // 推荐分
                        replyInfo.setRecommendScore(Integer.valueOf(split[0]));
                        // 赞踩数
                        replyInfo.setAgreeCount(Integer.valueOf(split[1]));
                        replyInfo.setDisagreeCount(Integer.valueOf(split[2]));
                    }


                }

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
     * 参数列表预处理，将参数中的逗号替换为其他分隔符
     * @param s     参数列表
     * @param split 分隔符
     * @return java.lang.String
     * @since 2023/5/5 9:18
     */
    private static String preHandle(String s, String split) {
        List<String> rawList = new ArrayList<>();
        final Matcher matcher = SCRIPT_PATTERN.matcher(s);
        while (matcher.find()) {
            final String group = matcher.group(1);
            if (group.contains(",")) {
                rawList.add(group);
            }
        }
        if (rawList.size() > 0) {
            for (String raw : rawList) {
                s = s.replace(raw, raw.replace(",", split));
            }
        }
        return s;
    }

    /**
     * 从网页中解析数据
     * @param root 根元素
     */
    public void parseElement(Element root) {
        final String rootString = HtmlUtils.clearLinkBreak(root.toString());
        // 作者uid
        handleElement(root, "postauthor" + this.floorNumber, e -> this.authorUid = parseUidFromA(e));

        // 发表日期 发表时间戳
        handleElement(root, "postdate" + this.floorNumber, e -> this.postDate = e.ownText());
        //标题
        handleElement(root, "postsubject" + this.floorNumber, e -> this.title = e.ownText());

        // 回复正文
        handleElement(root, "postcontent" + this.floorNumber, e -> setContent(handleContent(e)));

        //改动信息
        {
            final Matcher matcher = ALTER_INFO_PATTERN.matcher(rootString);
            if (matcher.find()) {
                final String s = matcher.group(1).replace("||", "");
                this.alterInfo = new AlterInfo(s);
            }
        }

        // 贴条
        handleElement(root, "comment_for_" + this.replyId, e -> {
            this.comment = parseComment(e);
            this.comment.forEach((k, v) -> v.setTopicId(this.topicId));
        });
        // 热评
        handleElement(root, "hightlight_for_0", e -> {
            this.hotReplies = parseComment(e);
            this.hotReplies.forEach((k, v) -> v.setTopicId(this.topicId));
        });
        //附件信息
        {
            final Matcher matcher = ATTACH_PATTERN.matcher(rootString);
            if (matcher.find()) {
                try {
                    final String group = matcher.group(1)
                            .replaceAll("([,{])(\\w+?):", "$1\"$2\":")
                            .replace("'", "\"");
                    List<Attachment> list = NgaRes.MAPPER.readValue(group, new TypeReference<>() {
                    });
                    this.attachments = new LinkedHashMap<>();
                    for (int i = 0; i < list.size(); i++) {
                        this.attachments.put(i, list.get(i));
                    }
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        }
        //  回复或引用的id
        {
            final Matcher matcher = REPLY_TO_PATTERN.matcher(this.content);
            if (matcher.find()) {
                this.replyToId = Long.valueOf(matcher.group(1));
            }
        }
        // 回复pid script已填写
        // 赞数 script已填写
        // 回复id script已填写
        // 类型 script已填写
//        System.out.println(rootString);
    }

    /**
     * 解析script标签中的数据
     * @param script 参数列表
     */
    public void parseScript(String script) {
        // 预处理
        script = preHandle(script, SP);

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
        // type
        this.type = Integer.valueOf(params.get(4));
        // 作者uid
        this.authorUid = Long.valueOf(params.get(6));
        // 发布时间
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

}
