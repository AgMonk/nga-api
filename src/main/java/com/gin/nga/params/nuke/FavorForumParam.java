package com.gin.nga.params.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 14:41
 */
@Getter
@Setter
public class FavorForumParam extends NukeBaseParam {
    /**
     * 操作类型
     */
    @JsonProperty("action")
    String action;
    /**
     * 版面id,添加和移除时填写
     */
    @JsonProperty("fid")
    Long forumId;
    /**
     * 合集id,添加和移除时填写
     */
    @JsonProperty("stid")
    Long colTid;

    public FavorForumParam() {
        this.lib = "forum_favor2";
        this.act = "forum_favor";
    }
}
