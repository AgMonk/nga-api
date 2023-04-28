package com.gin.nga.response.field;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.common.utils.JacksonUtils;
import com.gin.nga.deserializer.UserFieldInReadDeserializer;
import com.gin.nga.response.NgaRes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 用户信息上下文
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 10:58
 */
@Getter
@Setter
@JsonDeserialize(using = UserFieldInReadDeserializer.class)
@NoArgsConstructor
public class UserContext {

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

    /**
     * 常规用户信息 uid->info
     */
    Map<Long, UserInfoRead> userInfo = new LinkedHashMap<>();
    /**
     * 匿名用户信息 临时id->info
     */
    Map<Integer, AnonymousUser> anonymousUserInfo = new LinkedHashMap<>();
    /**
     * 用户组信息 id->info
     */
    Map<Integer, UserGroup> groups = new LinkedHashMap<>();
    /**
     * 徽章信息 id->info
     */
    Map<Integer, Medal> medals = new LinkedHashMap<>();
    /**
     * 声望信息 id -> uid -> 声望
     */
    ReputationUser reputationUser;

    public UserContext(LinkedHashMap<String, Object> inputMap) {
        inputMap.forEach((key, obj) -> {
            if (GROUPS_FIELD.equals(key)) {
                //用户组信息
                if (obj instanceof LinkedHashMap<?, ?> map) {
                    map.forEach((k, v) -> {
                        final int id = Integer.parseInt(String.valueOf(k));
                        try {
                            final String s = NgaRes.MAPPER.writeValueAsString(v);
                            Map<Integer, Serializable> data = NgaRes.MAPPER.readValue(s, new TypeReference<>() {
                            });
                            groups.put(id, new UserGroup(data));
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
                            final String s = NgaRes.MAPPER.writeValueAsString(v);
                            Map<Integer, Serializable> data = NgaRes.MAPPER.readValue(s, new TypeReference<>() {
                            });
                            medals.put(id, new Medal(data));
                        } catch (JsonProcessingException e) {
                            e.printStackTrace();
                        }
                    });
                }
            } else if (REPUTATIONS_FIELD.equals(key)) {
                //声望信息
                try {
                    final String s = NgaRes.MAPPER.writeValueAsString(obj);
                    this.reputationUser = NgaRes.MAPPER.readValue(s, ReputationUser.class);
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
                anonymousUserInfo.put(index, anonymousUser);
            } else {
                // 常规用户信息
                try {
                    userInfo.put(Long.parseLong(key), JacksonUtils.parseObj(obj, UserInfoRead.class));
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
