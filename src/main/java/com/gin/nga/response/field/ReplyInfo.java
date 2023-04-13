package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.nga.serializer.ZdtJsonSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * 回复信息(read.php接口)
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 11:46
 */
@Getter
@Setter
public class ReplyInfo extends ReplySimple {
    /**
     * 修改记录，包括处罚、撤销处罚、编辑
     */
    @JsonProperty("alterinfo")
    String alterInfo;
    /**
     * 附件信息
     */
    @JsonProperty("attachs")
    List<Attachment> attachments;
    /**
     * 使用的客户端,PC,IOS,安卓
     */
    @JsonProperty("from_client")
    String fromClient;
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

}   
