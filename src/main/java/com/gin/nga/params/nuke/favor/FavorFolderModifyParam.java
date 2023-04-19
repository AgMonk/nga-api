package com.gin.nga.params.nuke.favor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.params.nuke.base.NukeBaseParam;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * 修改收藏夹列表参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 16:19
 */
@Getter
public class FavorFolderModifyParam extends NukeBaseParam {
    /**
     * 名称
     */
    @NotNull
    final String name;
    /**
     * 是否公开
     */
    @JsonProperty("opt")
    final int isPublic;
    /**
     * 收藏夹id
     */
    @JsonProperty("folder")
    final long folderId;

    public FavorFolderModifyParam(@NotNull String name, boolean isPublic, long folderId) {
        super("topic_favor_v2", "modify_folder");
        this.name = name;
        this.isPublic = isPublic ? 1 : 0;
        this.folderId = folderId;
    }
}
