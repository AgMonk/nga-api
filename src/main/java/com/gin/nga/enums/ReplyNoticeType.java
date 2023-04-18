package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

/**
 * 回复提醒类型
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 10:21
 */
@RequiredArgsConstructor
public enum ReplyNoticeType {
    /**
     * 对主题
     */
    topic("对主题", 1),
    /**
     * 对回复
     */
    reply("对回复", 2),
    /**
     * at你
     */
    at("@你", 8),
    /**
     * 送礼物
     */
    gift("送礼物", 15),

    ;
    @JsonValue
    public final String name;
    public final int id;

    @JsonCreator
    public static ReplyNoticeType getById(Integer id) {
        if (id == null) {
            return null;
        }
        for (ReplyNoticeType type : values()) {
            if (type.id == id) {
                return type;
            }
        }
        return null;
    }
}
