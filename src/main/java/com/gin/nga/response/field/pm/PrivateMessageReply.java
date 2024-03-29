package com.gin.nga.response.field.pm;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.jackson.serializer.ZdtJsonSerializer;
import com.gin.nga.bbscode.entity.BbsTag;
import com.gin.nga.bbscode.utils.BbsTagParser;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.List;

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
     * 参与用户信息(仅在第一条出现)
     */
    LinkedHashMap<Integer,String> data;
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

    public List<BbsTag> getContentNodes() {
        return content == null ? null : BbsTagParser.parseContent(content);
    }
}
