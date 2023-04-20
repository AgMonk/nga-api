package com.gin.nga.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.List;

/**
 * 附件字段的序列化方式
 */
public class ListAttachSerializer extends JsonSerializer<List<String>> {
    @Override
    public void serialize(List<String> ids, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(String.join("\t",ids));
    }
}