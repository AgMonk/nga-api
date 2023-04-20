package com.gin.nga.params.nuke.pm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.nuke.base.NukeBaseParam;
import lombok.Getter;

/**
 * 私信基础参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 12:36
 */
@Getter
public class PrivateMessageBaseParam extends NukeBaseParam {
    @JsonProperty("act")
    final String action;

    public PrivateMessageBaseParam(String action) {
        super("message", "message");
        this.action = action;
    }
}
