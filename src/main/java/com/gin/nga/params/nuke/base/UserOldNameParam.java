package com.gin.nga.params.nuke.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * @author bx002
 * @since 2024/2/17 9:36
 */
@Getter
public class UserOldNameParam extends NukeBaseParam {
    @JsonProperty("uid")
    final long userId;
    public UserOldNameParam(long userId) {
        super("ucp","oldname");
        this.userId = userId;
    }
}
