package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ZdtJsonSerializer;
import com.gin.nga.enums.FromClient;
import com.gin.nga.utils.QueryStringUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jsoup.nodes.Element;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;

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

    // todo 贴条

    /**
     * 从网页解析回复信息
     * @param element 网页标签
     */
    public ReplyInfo(int index, Element root) {
        //todo

        // 作者uid
        {
            final Element e = root.getElementById("postauthor" + index);
            if (e!=null) {
                final String href = e.attr("href");
                final HashMap<String, Object> param = QueryStringUtils.parse(href.substring(href.indexOf("?") + 1));
                final Object uid = param.get("uid");
                if (uid!=null) {
                    this.authorUid = Long.valueOf(String.valueOf(uid));
                }
            }
        }

        //todo 改动信息
        //todo 附件信息
        //todo 热评
        //todo 贴条
        //todo 客户端
        //todo 楼层号
        //todo 回复pid
        //todo 发表日期
        //todo 发表时间戳
        //todo 赞数
        //todo 回复正文
        //todo 回复id
        //todo 回复或引用的id
        //todo 标题
        //todo 主题id
        //todo 类型

    }
}
