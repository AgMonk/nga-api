package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

/**
 * 布尔参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 17:31
 */
@RequiredArgsConstructor
public enum BoolParam {
    /**
     * 是
     */
    yes(1),
    /**
     * 否
     */
    no(0)
    ;
    @JsonValue
    public final int code;
}
