package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
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
    UNKNOWN(""),
    ;
    public final String value;
    @JsonCreator
    public static  FromClient findByValue(String value){
        for (FromClient item : values()) {
            if (item.value.equals(value)) {
                return item;
            }
        }
        return UNKNOWN;
    }
}   
