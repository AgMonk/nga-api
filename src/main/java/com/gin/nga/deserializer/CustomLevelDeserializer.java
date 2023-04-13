package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.common.utils.JacksonUtils;
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
        final String value = jsonParser.getValueAsString()
                .replace("{r:","{\"r\":")
                .replace(",n:",",\"n\":")
                ;
        return JacksonUtils.MAPPER.readValue(value, new TypeReference<>() {
        });
    }
}
