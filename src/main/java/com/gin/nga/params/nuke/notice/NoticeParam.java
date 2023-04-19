package com.gin.nga.params.nuke.notice;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.nuke.base.NukeBaseParam;
import lombok.Getter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 09:21
 */
@Getter
public class NoticeParam extends NukeBaseParam {
    final int raw = 3;
    @JsonProperty("time_limit")
    final long timeLimit = 1;

    public NoticeParam() {
super("noti","get_all");
    }
}
