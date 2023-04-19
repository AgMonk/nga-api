package com.gin.nga.params.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.nuke.base.NukeBaseParam;
import lombok.Getter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 11:27
 */
@Getter
public class UserInfoParam extends NukeBaseParam {
    /**
     * 用户id
     */
    @JsonProperty("uid")
  final   Long userId;
    /**
     * 用户名
     */
    @JsonProperty("username")
    final String username;

    public UserInfoParam(Long userId) {
        this(userId, null);
    }

    public UserInfoParam(String username) {
        this(null, username);
    }

    private UserInfoParam(Long userId, String username) {
        super("ucp", "get");
        this.userId = userId;
        this.username = username;
    }
}
