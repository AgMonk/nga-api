package com.gin.nga.response.field.notice;

import com.fasterxml.jackson.annotation.JsonAlias;
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
public abstract class BaseNotice {
    /**
     * 回复时间
     */
    @JsonAlias("9")
    ZonedDateTime timestamp;
    /**
     * 生成描述信息
     * @return 描述信息
     */
    public abstract String getDescription();

}
