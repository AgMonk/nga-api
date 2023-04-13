package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * 声望反序列化
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 17:06
 */
public class ReputationDeserializer extends JsonDeserializer<LinkedHashMap<Integer, Integer>> {
    @Override
    public LinkedHashMap<Integer, Integer> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final String value = p.getValueAsString();
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        final LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        for (String pair : value.split(",")) {
            final String[] split = pair.split("_");
            map.put(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        }
        return map;
    }
}
