package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

/**
 * 隐藏发帖
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/24 14:43
 */
@RequiredArgsConstructor
 public enum Hidden {
    /**
     * 隐藏
     */
    HIDDEN(1),
    /**
     * 取消隐藏
     */
    CANCEL(2)

    ;
    @JsonValue
    public final int id;
}
