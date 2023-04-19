package com.gin.nga.params.nuke.favorforum;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.enums.FavorAction;
import com.gin.nga.enums.FavorType;
import com.gin.nga.params.nuke.base.NukeBaseParam;
import lombok.Getter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 14:41
 */
@Getter
public class FavorForumParam extends NukeBaseParam {
    /**
     * 操作类型
     */
    @JsonProperty("action")
    final String action;
    /**
     * 版面id,添加和移除时填写
     */
    @JsonProperty("fid")
    final Long forumId;
    /**
     * 合集id,添加和移除时填写
     */
    @JsonProperty("stid")
    final Long colTid;

    /**
     * 查询参数
     */
    public FavorForumParam() {
        this("get", null, null);
    }

    private FavorForumParam(String action, Long forumId, Long colTid) {
        super("forum_favor2", "forum_favor");
        this.action = action;
        this.forumId = forumId;
        this.colTid = colTid;
    }
    public FavorForumParam(FavorAction action, FavorType type, long id) {
        this(action.name()
                , type == FavorType.forum ? id : null
                , type == FavorType.col ? id : null
        );
    }
}
