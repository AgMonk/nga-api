package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ZdtJsonSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * 回复(简单数据), 出现在收藏回复中
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 12:23
 */
@Getter
@Setter
public class ReplySimple {
    /**
     * 作者uid
     */
    @JsonProperty("authorid")
    Long authorUid;
    /**
     * 回复正文
     */
    @JsonProperty("content")
    String content;
    /**
     * 回复id
     */
    @JsonProperty("pid")
    Long pid;
    /**
     * 发表时间
     */
    @JsonProperty("postdate")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime postDatetime;
    /**
     * 标题
     */
    @JsonProperty("subject")
    String title;
    /**
     * 主题id
     */
    @JsonProperty("tid")
    Long topicId;
    /**
     * 类型
     */
    @JsonProperty("type")
    Long type;

}   
