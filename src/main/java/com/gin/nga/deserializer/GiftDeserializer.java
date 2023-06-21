package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.nga.response.field.Gift;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/15 12:41
 */
public class GiftDeserializer extends JsonDeserializer<List<Gift>> {
    @Override
    public List<Gift> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final ArrayList<Gift> res = new ArrayList<>();
        LinkedHashMap<Integer,Integer> map = p.getCodec().readValue(p, new TypeReference<LinkedHashMap<Integer, Integer>>() {
        });
        map.forEach((k,v)->res.add(new Gift(k,v)));
        return res;
    }
}
