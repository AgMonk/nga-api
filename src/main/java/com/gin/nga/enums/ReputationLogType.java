package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

/**
 * 声望操作类型
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/14 09:47
 */
@RequiredArgsConstructor
public enum ReputationLogType {
    /**
     * 处罚
     */
    punishment("处罚"),
    /**
     * 加分/扣分
     */
    reward("加分/扣分"),
    /**
     * 取消操作
     */
    canceled("取消操作"),

    ;
    @JsonValue
    public  final String value;
}
