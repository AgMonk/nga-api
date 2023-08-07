package com.gin.nga.params.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.nuke.base.NukeBaseParam;
import lombok.Getter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/8/7 09:38
 **/
@Getter
public class LoadTopicParam extends NukeBaseParam {
    @JsonProperty("uid")
    final long userId;
    public LoadTopicParam(long userId) {
        super("load_topic", "load_topic_by_uid");
        this.userId = userId;
    }
}
