package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.RequiredArgsConstructor;

/**
 * 收藏夹类型
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 17:14
 */
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum FavorFolderType {

    /**
     * 公开的
     */
    PUBLIC(1,"公开的"),
    /**
     * 私有的
     */
    PRIVATE(0,"私有的"),
    /**
     * 未知
     */
    DEFAULT(2,"默认"),
    ;
    public  final int id;
    public  final String value;
    @JsonCreator
    public static FavorFolderType findById(int id){
        for (FavorFolderType value : values()) {
            if (value.id==id) {
                return value;
            }
        }
        return null;
    }
}   
