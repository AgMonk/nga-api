package com.gin.nga.params.nuke.follow;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.nuke.base.NukeBaseParam;
import lombok.Getter;
import lombok.Setter;

/**
 * 查询关注用户列表
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/8/7 09:55
 **/
@Getter
@Setter
public class FollowGetParam extends NukeBaseParam {
    @JsonProperty("page")
    int page;
    public FollowGetParam(int page) {
        super("follow_v2", "get_follow");
        this.page = page;
    }
}
