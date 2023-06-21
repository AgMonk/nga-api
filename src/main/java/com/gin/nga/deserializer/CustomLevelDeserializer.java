package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.field.CustomLevel;

import java.io.IOException;
import java.util.List;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 14:00
 */
public class CustomLevelDeserializer extends JsonDeserializer<List<CustomLevel>> {
    @Override
    public List<CustomLevel> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        return parse(jsonParser.getValueAsString());
    }

    /**
     * 规范化
     * @param s s
     * @return s
     */
    public static String format(String s){
        return s.replace("{r:","{\"r\":")
                .replace(",n:",",\"n\":");
    }

    public static List<CustomLevel> parse(String s) throws JsonProcessingException {
       return NgaRes.MAPPER.readValue(format(s), new TypeReference<List<CustomLevel>>() {
        });
    }
}
