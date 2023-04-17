package com.gin.nga.params.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 屏蔽的子版面参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 15:46
 */
@Setter
@Getter
public class BlockSubForumParam extends NukeBaseParam {
    final String indo = "add_to_block_tids";
    final int raw = 3;
    final int type = 1;
    /**
     * 父版面id
     */
    @JsonProperty("fid")
    Long forumId;
    /**
     * 取消屏蔽,填写 {@link com.gin.nga.response.field.SubForum} 的 mirrorId 字段，或主题列表中的 {@link  com.gin.nga.response.field.TopicInfo} 的 topicId 字段
     */
    @JsonProperty("del")
    Long delId;
    /**
     * 添加屏蔽,填写 {@link com.gin.nga.response.field.SubForum} 的 mirrorId 字段，或主题列表中的 {@link  com.gin.nga.response.field.TopicInfo} 的 topicId 字段
     */
    @JsonProperty("add")
    Long addId;

    public BlockSubForumParam() {
        this.lib = "user_option";

    }
}
