package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 计数器类型
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 13:08
 */
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.STRING)
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

    public final int id;

    @JsonCreator
    public static MissionCounterType findById(int id){
        return Arrays.stream(values()).filter(i -> i.id == id).findFirst().orElse(null);
    }
}
