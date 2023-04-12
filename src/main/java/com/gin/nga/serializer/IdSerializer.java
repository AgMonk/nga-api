package com.gin.nga.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * id序列化方法,将id用逗号连接
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/12 10:02
 */
public class IdSerializer extends JsonSerializer<List<Long>> {
    @Override
    public void serialize(List<Long> ids, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(ids.stream().map(String::valueOf).collect(Collectors.joining(",")));
    }
}
