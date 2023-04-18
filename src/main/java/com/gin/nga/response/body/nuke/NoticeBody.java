package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ZdtJsonSerializer;
import com.gin.nga.response.field.notice.MessageNotice;
import com.gin.nga.response.field.notice.ReplyNotice;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 提醒消息body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 09:24
 */
@Getter
@Setter
public class NoticeBody {
    @JsonAlias("0")
    List<ReplyNotice> replyNotice;
    @JsonAlias("1")
    List<MessageNotice> msgNotice;

    /**
     * 获得时间
     */
    @JsonAlias("lasttime")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime timestamp;

    public static class Res extends LinkedHashMap<Integer, NoticeBody> {
    }
}   
