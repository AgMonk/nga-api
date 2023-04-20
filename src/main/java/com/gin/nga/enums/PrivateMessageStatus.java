package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 私信状态
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
    MULTI(0, "多人"),
    /**
     * 未读
     */
    UNREAD(1, "未读"),
    UNKNOWN_1(3, "未知(可能为'来自系统')"),
    UNKNOWN_2(5, "未知"),
    ;
    @JsonIgnore
    public final int position;
    public final String name;

    /**
     * 从 type 字段解析状态列表
     * @param type type字段
     * @return 状态列表
     */
    public static List<PrivateMessageStatus> parse(int type) {
        if (type == 0) {
            return null;
        }
        String s = new StringBuilder(Integer.toBinaryString(type)).reverse().toString();
        List<PrivateMessageStatus> list = new ArrayList<>();
        for (PrivateMessageStatus i : values()) {
            if (s.length() > i.position && s.charAt(i.position) == '1') {
                list.add(i);
            }
        }
        return list;
    }
}
