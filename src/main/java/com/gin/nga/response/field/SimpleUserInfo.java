package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
