package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 发帖动作
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 09:43
 */
public enum PostAction {
    /**
     * 新主题
     */
    NEW,
    /**
     * 回复或评论(贴条)
     */
    REPLY,
    /**
     * 引用
     */
    QUOTE,
    /**
     * 编辑
     */
    MODIFY,

    ;
    @JsonValue
    public String getFieldName(){
        return name().toLowerCase();
    }
}
