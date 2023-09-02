package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 私信状态 <a href="https://img4.nga.178.com/common_res/js_message.js">来源</a>
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 14:38
 */
@RequiredArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PrivateMessageStatus {
    /**
     * 对人对话
     */
    MULTI(1, "多人"),
    /**
     * 未读
     */
    UNREAD(2, "未读"),
    UNKNOWN(4, "未知"),
    FROM_SYSTEM(8, "系统消息"),
    UNKNOWN_2(16, "未知"),

    ANONYMOUS_MODE(32, "对话参与者的回复互相不可见"),
    ;
    @JsonIgnore
    public final int bit;
    public final String name;

    /**
     * 从 bit 字段解析状态列表
     * @param bit type字段
     * @return 状态列表
     */
    public static List<PrivateMessageStatus> parseStatus(int bit){
        final ArrayList<PrivateMessageStatus> list = new ArrayList<>();
        for (PrivateMessageStatus status : values()) {
            if ((status.bit & bit) !=0){
                list.add(status);
            }
        }
        return list;
    }
}
