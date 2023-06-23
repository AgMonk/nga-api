package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

/**
 * 道具类型
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/23 10:59
 */
@RequiredArgsConstructor
public enum ItemType {
    /**
     * 激活码
     */
    ACTIVATION_CODE(1),
    /**
     * 徽章
     */
    MEDAL(2),
    /**
     * 对人道具
     */
    TO_USER(3),
    /**
     * 对贴道具
     */
    TO_REPLY(5),

    ;
    @JsonValue
    public final int id;
}
