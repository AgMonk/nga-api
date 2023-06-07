package com.gin.nga.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 入口类型
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/7 12:14
 */
@Getter
@RequiredArgsConstructor
public enum EntranceType {
    TOPIC("主题"),
    FORUM("版面"),
    COL("合集"),

    ;
    public final String name;
}
