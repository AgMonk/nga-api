package com.gin.nga.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.io.JsonEOFException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gin.common.utils.JacksonUtils;
import com.gin.nga.exception.NgaServerException;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * 统一响应对象
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/11 11:13
 */
@Getter
@Setter
public class NgaRes<T> {
    Map<Integer, String> error;
    T data;
    String encode;
    long time;

    /**
     * 解析字符串返回data字段
     * @param <T>   data字段类型
     * @param s     响应字符串
     * @param clazz data字段的class
     * @return data字段
     * @throws JsonProcessingException 解析异常
     */
    public static <T> NgaRes<T> parse(String s, Class<T> clazz) throws JsonProcessingException, NgaServerException {
        final NgaServerException exception = new NgaServerException(500, null, "响应信息不完整, 请稍后再试");
        try {
            final JavaType javaType = JacksonUtils.MAPPER.getTypeFactory().constructParametricType(NgaRes.class, clazz);
            return JacksonUtils.MAPPER.readValue(s, javaType);
        } catch (JsonEOFException  e) {
            // 错误的结束符号
            throw exception;
        } catch (JsonMappingException e){
            if (e.getCause() instanceof JsonEOFException) {
                // 错误的结束符号
                throw exception;
            }
            throw e;
        }
    }
}   
