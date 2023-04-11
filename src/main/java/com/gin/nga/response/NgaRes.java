package com.gin.nga.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.gin.common.utils.JacksonUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * 统一响应对象
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 11:13
 */
@Getter
@Setter
public class NgaRes<T> {
    T data;
    long time;

    /**
     * 解析字符串返回data字段
     * @param s 响应字符串
     * @param clazz data字段的class
     * @return data字段
     * @param <T> data字段类型
     * @throws JsonProcessingException 解析异常
     */
    public static <T> T parse(String s, Class<T> clazz) throws JsonProcessingException {
        final JavaType javaType = JacksonUtils.MAPPER.getTypeFactory().constructParametricType(NgaRes.class, clazz);
        final NgaRes<T> response = JacksonUtils.MAPPER.readValue(s, javaType);
        return response.getData();
    }
}   
