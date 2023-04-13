package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.common.utils.JacksonUtils;
import com.gin.nga.response.field.*;

import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * read.php接口中的__U字段反序列化器
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 10:57
 */
public class UserFieldInReadDeserializer extends JsonDeserializer<UserFieldInRead> {
    /**
     * 匿名前缀
     */
    public static final String ANONYMOUS_PREFIX = "-";
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
        jsonParser.getCodec().<LinkedHashMap<String, Object>>readValue(jsonParser, new TypeReference<>() {
        }).forEach((key, obj) -> {
            if (GROUPS_FIELD.equals(key)) {
                //用户组信息
                if (obj instanceof LinkedHashMap<?, ?> map) {
                    map.forEach((k, v) -> {
                        final int id = Integer.parseInt(String.valueOf(k));
                        try {
                            final String s = JacksonUtils.MAPPER.writeValueAsString(v);
                            Map<Integer, Serializable> data = JacksonUtils.MAPPER.readValue(s, new TypeReference<>() {
                            });
                            res.getGroups().put(id, new UserGroup(data));
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } else if (MEDALS_FIELD.equals(key)) {
                //徽章信息
                if (obj instanceof LinkedHashMap<?, ?> map) {
                    map.forEach((k, v) -> {
                        final int id = Integer.parseInt(String.valueOf(k));
                        try {
                            final String s = JacksonUtils.MAPPER.writeValueAsString(v);
                            Map<Integer, Serializable> data = JacksonUtils.MAPPER.readValue(s, new TypeReference<>() {
                            });
                            res.getMedals().put(id, new Medal(data));
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } else if (REPUTATIONS_FIELD.equals(key)) {
                //声望信息
                try {
                    final String s = JacksonUtils.MAPPER.writeValueAsString(obj);
                    res.setReputations(JacksonUtils.MAPPER.readValue(s, new TypeReference<>() {
                    }));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

            } else if (key.startsWith(ANONYMOUS_PREFIX)) {
                // 匿名用户信息
                final String username = String.valueOf(JacksonUtils.jsonToMap(obj).get("username"));
                final AnonymousUser anonymousUser = new AnonymousUser();
                final int index = Integer.parseInt(key);
                anonymousUser.setIndex(index);
                anonymousUser.setUsername(username);
                res.getAnonymousUserInfo().put(index, anonymousUser);
            } else {
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
