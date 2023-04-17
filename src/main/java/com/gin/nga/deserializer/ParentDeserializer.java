package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.nga.response.field.TopicParent;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;

/**
 * TopicParent 反序列化
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 09:42
 */
public class ParentDeserializer  extends JsonDeserializer<TopicParent> {

    @Override
    public TopicParent deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        LinkedHashMap<Integer, Serializable> map = p.getCodec().readValue(p, new TypeReference<>() {
        });
        final TopicParent topicParent = new TopicParent();
        topicParent.setForumId(parseLong(map.get(0)));
        topicParent.setColTid(parseLong(map.get(1)));
        topicParent.setName(parseString(map.get(2)));
        return topicParent;
    }

    private static String parseString(Serializable s){
        if (s==null) {
            return null;
        }
        return String.valueOf(s);
    }

    private static Long parseLong(Serializable s){
        if (s==null) {
            return null;
        }
        return Long.parseLong(String.valueOf(s));
    }
}
