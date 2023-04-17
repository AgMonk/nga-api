package com.gin.nga.params.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 11:27
 */
@Getter
@Setter
public class UserInfoParam extends NukeBaseParam {
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

    private  UserInfoParam() {
        this.lib = "ucp";
        this.action = "get";
    }

    public UserInfoParam(Long userId) {
        this();
        this.userId = userId;
    }

    public UserInfoParam(String username) {
        this();
        this.username = username;
    }
}
