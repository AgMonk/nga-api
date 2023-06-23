package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.nga.response.field.item.ItemData;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;


/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/6/23 10:24
 */
public class ItemStoreDataDeserializer extends JsonDeserializer<ItemData> {

    @Override
    public ItemData deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        LinkedHashMap<String, Serializable> map = jsonParser.getCodec().readValue(jsonParser, new TypeReference<LinkedHashMap<String, Serializable>>() {
        });
        return new ItemData(map);
    }
}
    