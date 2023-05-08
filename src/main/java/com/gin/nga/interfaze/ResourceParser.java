package com.gin.nga.interfaze;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * 静态资源解析器
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/8 10:18
 */
public interface ResourceParser<T> {
    /**
     * 将字符串解析为指定类型
     * @param s 响应字符串
     * @param clazz 目标类型
     * @return T
     */
    T parse(String s,Class<T> clazz) throws JsonProcessingException;
}   
