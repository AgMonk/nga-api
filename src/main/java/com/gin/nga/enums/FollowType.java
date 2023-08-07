package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

/**
 * 关注类型  <a href="https://img4.nga.178.com/common_res/js_commonui.js">来自</a>
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/8/7 09:56
 **/
@RequiredArgsConstructor
public enum FollowType {
    /**
     * 关注用户
     */
    FOLLOW_USER(1),
    /**
     * 关注主题
     */
    FOLLOW_TOPIC(2),
    /**
     * 关注回复
     */
    FOLLOW_REPLY(4),
    /**
     * 取消关注用户
     */
    UNFOLLOW_USER(8),
    /**
     * 取消关注主题
     */
    UNFOLLOW_TOPIC(16),
    /**
     * 取消关注回复
     */
    UNFOLLOW_REPLY(32),
    /**
     * 关注用户的回复
     */
    FOLLOW_USER_REPLY(64),
    /**
     * 取消关注用户的回复
     */
    UNFOLLOW_USER_REPLY(128),

    ;
@JsonValue
    public final int value;
}
