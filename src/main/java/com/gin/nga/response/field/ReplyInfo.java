package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ZdtJsonSerializer;
import com.gin.nga.enums.FromClient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jsoup.nodes.Element;

import java.time.ZonedDateTime;
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
    LinkedHashMap<Integer,Attachment> attachments;
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
     * @param element  网页标签
     */
    public ReplyInfo(int index,Element element) {
        //todo
        System.out.println(element);
    }
}
