package com.gin.nga.params.nuke.pm;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.nuke.base.NukeBaseParam;
import lombok.Getter;

/**
 * 获取私信列表参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 12:36
 */
@Getter
public class PrivateMessageListParam extends NukeBaseParam {
    @JsonProperty("act")
    final String action = "list";
    final int page;

    public PrivateMessageListParam(int page) {
        super("message", "message");
        this.page = Math.max(1, page);
    }
}
