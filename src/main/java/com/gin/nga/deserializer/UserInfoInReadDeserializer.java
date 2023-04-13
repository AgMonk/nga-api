package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.nga.response.field.UserInfoInRead;

import java.io.IOException;

/**
 * read.php接口中的__U字段反序列化器
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 10:57
 */
public class UserInfoInReadDeserializer extends JsonDeserializer<UserInfoInRead> {

    @Override
    public UserInfoInRead deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        //todo
        return null;
    }
}
