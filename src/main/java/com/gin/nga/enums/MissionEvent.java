package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * 任务触发类型
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 14:52
 */
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum MissionEvent {
    /**
     *  签到事件
     */
    SIGN_IN(1),
    /**
     *   主题分享事件
     */
    TOPIC_SHARE(2),
    /**
     *   组队事件
     */
    BUILD_TEAM(3),
    /**
     *   未知
     */
    CUSTOM_SUPPORT(4),

    ;
    public final int id;

    @JsonCreator
    public static MissionEvent findById(int id){
        return Arrays.stream(values()).filter(i -> i.id == id).findFirst().orElse(null);
    }

}   
