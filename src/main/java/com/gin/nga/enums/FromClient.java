package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

/**
 * 用户使用的客户端
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 14:12
 */
@RequiredArgsConstructor
public enum FromClient {
    /**
     * IOS
     */
    IOS("7 iOS"),
    /**
     * 安卓
     */
    Android("8 Android"),
    /**
     * PC
     */
    PC("0 /"),
    ;
    @JsonValue
    public final String value;
}   
