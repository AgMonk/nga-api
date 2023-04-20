package com.gin.nga.response.field.pm;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ZdtJsonSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * 私信回复
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 15:28
 */
@Getter
@Setter
public class PrivateMessageReply {
    long id;
    /**
     * 标题
     */
    @JsonAlias("subject")
    String title;
    /**
     * 回复正文
     */
    @JsonAlias("content")
    String content;
    /**
     * uid
     */
    @JsonAlias("from")
    Long userId;
    /**
     * 时间戳
     */
    @JsonAlias("time")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime timestamp;

}
