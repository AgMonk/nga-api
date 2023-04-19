package com.gin.nga.params.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 09:21
 */
@Getter
@Setter
public class NoticeParam extends NukeBaseParam {
    final int raw = 3;
    @JsonProperty("time_limit")
    long timeLimit = 1;

    public NoticeParam() {
super("noti","get_all");
    }
}
