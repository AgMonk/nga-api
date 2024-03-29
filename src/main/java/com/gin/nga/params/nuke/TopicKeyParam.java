package com.gin.nga.params.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.nuke.base.NukeBaseParam;
import lombok.Getter;

/**
 * 获取主题分类信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 13:59
 */
@Getter
public class TopicKeyParam extends NukeBaseParam {
    @JsonProperty("fid")
    private final long forumId;

    public TopicKeyParam(long forumId) {
        super("topic_key","get");
        this.forumId = forumId;
    }
}
