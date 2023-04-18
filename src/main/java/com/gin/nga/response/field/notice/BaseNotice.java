package com.gin.nga.response.field.notice;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ZdtJsonSerializer;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

/**
 * 提醒消息的公共字段
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 10:02
 */
@Getter
@Setter
public class BaseNotice {
    /**
     * 未知数据
     */
    @JsonAlias("0")
    Long data;
    /**
     * 回复时间
     */
    @JsonAlias("9")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime timestamp;
}   
