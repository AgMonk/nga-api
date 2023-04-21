package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

/**
 * 计数器类型
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 13:08
 */
@RequiredArgsConstructor
public enum MissionCounterType {
    /**
     * 签到
     */
    SIGN_IN(1) ,
    /**
     * 主题分享
     */
    TOPIC_SHARE(2),
    /**
     * 组队
     */
    BUILD_TEAM (3),
    CUSTOM_SUPPORT (4),

    ;

    @JsonValue
    public final int id;
}
