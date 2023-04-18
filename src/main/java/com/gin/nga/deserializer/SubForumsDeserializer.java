package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.type.MapLikeType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.field.SubForum;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * 子版面反序列化
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/14 14:33
 */
public class SubForumsDeserializer extends JsonDeserializer<List<SubForum>> {
    @Override
    public List<SubForum> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final ArrayList<SubForum> res = new ArrayList<>();
        final TypeFactory typeFactory = NgaRes.MAPPER.getTypeFactory();
        final MapLikeType valueType = typeFactory.constructMapLikeType(LinkedHashMap.class, Integer.class, Serializable.class);
        final JavaType stringType = typeFactory.constructType(String.class);
        final MapLikeType type = typeFactory.constructMapLikeType(LinkedHashMap.class, stringType, valueType);
        LinkedHashMap<String, LinkedHashMap<Integer, Serializable>> forumMap = p.getCodec().readValue(p, type);
        forumMap.forEach((s, map) -> res.add(new SubForum(s, map)));
        return res;
    }
}
