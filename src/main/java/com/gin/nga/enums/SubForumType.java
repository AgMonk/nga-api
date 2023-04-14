package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

/**
 * 子版面类型
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/14 12:36
 */
@RequiredArgsConstructor
public enum SubForumType {
    /**
     * 合集
     */
    COL( "合集"),
    /**
     * 版面
     */
    FORUM("版面"),
    ;
    /**
     * 类型名
     */
    @JsonValue
    public final String name;

}
