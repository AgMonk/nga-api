package com.gin.nga.params.nuke.favor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.nuke.base.NukeBaseParam;
import lombok.Getter;

/**
 * 查询收藏夹列表参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 16:19
 */
@Getter
public class FavorFolderListParam extends NukeBaseParam {
    final int page;
    /**
     * 留空表示自己
     */
    @JsonProperty("uid")
    final Long userId;

    public FavorFolderListParam(int page) {
        this(page, null);
    }

    public FavorFolderListParam(int page, Long userId) {
        super("topic_favor_v2","list_folder");
        this.page = Math.max(1,page);
        this.userId = userId;
    }
}
