package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.nga.response.field.UserBuff;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * 用户buff反序列化
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 12:06
 */
public class UserBuffDeserializer extends JsonDeserializer<LinkedHashMap<Integer, UserBuff>> {
    @Override
    public LinkedHashMap<Integer, UserBuff> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        LinkedHashMap<Integer,  LinkedHashMap<Integer, Serializable>> maps = p.getCodec().readValue(p, new TypeReference<>() {
                                                                                                    });
        final LinkedHashMap<Integer, UserBuff> res = new LinkedHashMap<>();

        maps.forEach((k,v)->res.put(k,new UserBuff(v)));
        return res;
    }
}
