package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * 用户个人版反序列化
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 12:55
 */
public class UserForumDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        LinkedHashMap<Integer, Serializable> map = p.getCodec().readValue(p,new TypeReference<>(){});
        if (map==null) {
            return null;
        }
        return String.valueOf(map.get(1));
    }
}
