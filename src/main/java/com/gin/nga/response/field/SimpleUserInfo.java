package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.nga.deserializer.UserAvatarDeserializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * 简单用户信息，uid+用户名
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 14:04
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUserInfo {
    /**
     * 用户id
     */
    @JsonProperty("uid")
    Long userId;
    /**
     * 用户名
     */
    @JsonProperty("username")
    String username;
    /**
     * 头像
     */
    @JsonProperty("avatar")
    @JsonDeserialize(using = UserAvatarDeserializer.class)
    List<String> avatars;

}
