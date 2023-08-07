package com.gin.nga.params.nuke.follow;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.enums.FollowType;
import com.gin.nga.params.nuke.base.NukeBaseParam;
import lombok.Getter;
import lombok.Setter;

/**
 * 关注参数
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/8/7 09:55
 **/
@Getter
@Setter
public class FollowParam extends NukeBaseParam {
    @JsonProperty("id")
    long id;
    @JsonProperty("type")
    FollowType type;
    public FollowParam(FollowType type, long id) {
        super("follow_v2", "follow");
        this.type = type;
        this.id = id;
    }
}
