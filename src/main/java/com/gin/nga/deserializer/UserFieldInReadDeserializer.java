package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.common.utils.JacksonUtils;
import com.gin.nga.response.field.UserFieldInRead;
import com.gin.nga.response.field.UserInfoInRead;

import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * read.php接口中的__U字段反序列化器
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 10:57
 */
public class UserFieldInReadDeserializer extends JsonDeserializer<UserFieldInRead> {
    /**
     * 用户组信息字段名
     */
    public static final String GROUPS_FIELD = "__GROUPS";
    /**
     * 徽章信息字段名
     */
    public static final String MEDALS_FIELD = "__MEDALS";
    /**
     * 声望信息字段名
     */
    public static final String REPUTATIONS_FIELD = "__REPUTATIONS";

    @Override
    public UserFieldInRead deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        final UserFieldInRead res = new UserFieldInRead();

        LinkedHashMap<String, Object> treeNode = jsonParser.getCodec().readValue(jsonParser, new TypeReference<>() {
        });
        //todo
        treeNode.forEach((key, obj) -> {
            if (GROUPS_FIELD.equals(key)) {
                //todo 用户组信息
            }else
            if (MEDALS_FIELD.equals(key)) {
                //todo 徽章信息
            }else
            if (REPUTATIONS_FIELD.equals(key)) {
                //todo 声望信息
            }else
            if (key.startsWith("-")){
                // todo 匿名用户信息
            }else{
                // 常规用户信息
                try {
                    res.getUserInfo().put(Long.parseLong(key), JacksonUtils.parseObj(obj, UserInfoInRead.class));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
        return res;
    }
}
