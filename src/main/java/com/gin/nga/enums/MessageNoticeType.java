package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

/**
 * 私信提醒类型
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 10:13
 */
@RequiredArgsConstructor
public enum MessageNoticeType {

    /**
     * 发起对话
     */
    created("发起对话",10),
    /**
     * 回复对话
     */
    replied("回复对话",11),
    ;
    final String name;
    @JsonValue
    final Integer id;

    @JsonCreator
    public static MessageNoticeType getById(Integer id){
        if (id==null) {
            return null;
        }
        for (MessageNoticeType type : values()) {
            if (type.id.equals(id)) {
                return type;
            }
        }
        return null;
    }
}
