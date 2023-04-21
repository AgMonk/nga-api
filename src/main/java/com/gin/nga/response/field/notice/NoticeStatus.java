package com.gin.nga.response.field.notice;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ZdtJsonSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * 提醒信息状态
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 16:56
 */
@Getter
@Setter
public class NoticeStatus {
    /**
     * 未读总数
     */
    @JsonAlias("0")
    Integer unread;

    /**
     * 未读回复提醒
     */
    @JsonAlias("2")
    Integer unreadReplies;
    /**
     * 未读私信提醒
     */
    @JsonAlias("3")
    Integer unreadPm;
    /**
     * 未读系统提醒
     */
    @JsonAlias("4")
    Integer unreadSystem;
    /**
     * 上一次获取提醒信息的时间
     */
    @JsonAlias("1")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime lastList;
}   
