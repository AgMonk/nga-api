package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

import java.util.Locale;

/**
 * 账号状态
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 15:44
 */
@RequiredArgsConstructor
public enum AccountStatus {
    /**
     * 未激活
     */
    NOT_ACTIVATED(0,"未激活"),
    /**
     * 已激活
     */
    ACTIVATED(1,"已激活"),
    /**
     * 被斩杀
     */
    NUKED(-1,"NUKED"),
    /**
     * 已注销
     */
    UNREGISTERED(-5,"已注销"),
    /**
     * 已激活(手机/关联)
     */
    ACTIVATED_PHONE(4,"已激活(手机/关联)"),


    ;
    @JsonValue
    public final int value;
    public final String label;


    @Override
    public String toString() {
        return String.format(Locale.CHINA, "%s(%d)", label, value);
    }

}
