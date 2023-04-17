package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户更多信息的备注
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 11:14
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserMoreInfoType {
    /**
     * 互动数
     */
    interactions(8,"互动数"),

    unknown(null,"未知");
    ;
    public Integer id;
    public final String name;

    UserMoreInfoType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
    @JsonCreator
    public static UserMoreInfoType findById(int id){
        for (UserMoreInfoType type : values()) {
            if (type.id!=null && id==type.id) {
                return type;
            }
        }
        unknown.id = id;
        return unknown;
    }
}   
