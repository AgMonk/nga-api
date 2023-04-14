package com.gin.nga.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;

/**
 * 链接类型
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/14 12:36
 */
@RequiredArgsConstructor
public enum NgaLinkType {
    /**
     * 主题
     */
    TOPIC("tid", "主题"),
    /**
     * 合集
     */
    COL("stid", "合集"),
    /**
     * 版面
     */
    FORUM("fid", "版面"),
    /**
     * 回复
     */
    REPLY("pid", "回复"),

    ;
    /**
     * 字段名
     */
    public final String field;
    /**
     * 类型名
     */
    @JsonValue
    public final String name;

    /**
     * 判断链接类型
     * @param ngaPhpApi  api类型
     * @param queryParam 查询参数
     * @return 链接类型
     */
    public static NgaLinkType find(NgaPhpApi ngaPhpApi, HashMap<String, Object> queryParam) {
        if (queryParam == null || queryParam.isEmpty()) {
            return null;
        }
        if (ngaPhpApi == NgaPhpApi.thread) {
            return queryParam.containsKey(COL.field) ? COL : FORUM;
        }
        if (ngaPhpApi == NgaPhpApi.read) {
            return queryParam.containsKey(REPLY.field) ? REPLY : TOPIC;
        }

        return null;
    }

}
