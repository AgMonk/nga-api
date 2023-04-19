package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;

/**
 * 用户buff的类型
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 15:30
 */
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserBuffType {
    /**
     * 权限相关
     */
    PERMISSION("权限", null, 129),
    /**
     * 禁言
     */
    MUTED("禁言", null, 105),
    /**
     * 变羊
     */
    SHEEP("变羊", "用户的头像应显示为羊", 99),
    /**
     * 变企鹅
     */
    EDIART("变企鹅", "用户的头像应显示为企鹅", 102),
    /**
     * 匿名
     */
    ANONY("匿名", "如果用户信息中有此buff则说明此信息是匿名发帖的假用户信息", 103),
    /**
     * 禁制
     */
    DISABLE_BUFF("禁制", "其他人无法对此用户使用道具 此用户也无法对其他人使用道具", 101),

    UNKNOWN("未知", null, null),
    ;
    public final String name;
    public final String description;
    public Integer id;

    @JsonCreator
    public static UserBuffType findById(int id) {
        for (UserBuffType type : values()) {
            if (type.id != null && id == type.id) {
                return type;
            }
        }
        UNKNOWN.id = id;
        return UNKNOWN;
    }
}