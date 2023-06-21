package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.jackson.utils.ObjectUtils;
import com.gin.nga.response.field.SimpleUserInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 私信参与用户反序列化
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 14:13
 */
public class PmUsersDeserializer extends JsonDeserializer<List<SimpleUserInfo>> {
    @Override
    public List<SimpleUserInfo> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return parse(p.getValueAsString());
    }

    public static  List<SimpleUserInfo> parse(String value){
        final ArrayList<SimpleUserInfo> res = new ArrayList<>();
        if (ObjectUtils.isEmpty(value)) {
            return res;
        }
        final String[] array = value.split("\\|\\|");
        for (int i = 0; i < array.length; i += 2) {
            res.add(new SimpleUserInfo(Long.parseLong(array[i]), array[i + 1],null));
        }
        return res;
    }
}
    