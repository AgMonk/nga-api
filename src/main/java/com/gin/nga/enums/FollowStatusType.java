package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

/**
 * 关注动态类型
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/8/7 10:22
 **/
@RequiredArgsConstructor
public enum FollowStatusType {
    /**
     *关注的用户发布了一个主题
     */
    TOPIC(1),
    /**
     *关注的用户发布了一个回复
     */
    REPLY(2),
    /**
     *关注的用户收藏了主题或回复
     */
    FAV(3),

    ;
    @JsonValue
    public final int value;
}
