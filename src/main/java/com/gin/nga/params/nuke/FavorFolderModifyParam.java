package com.gin.nga.params.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 修改收藏夹列表参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 16:19
 */
@Getter
@Setter
public class FavorFolderModifyParam extends NukeBaseParam {
    /**
     * 名称
     */
    String name;
    /**
     * 是否公开
     */
    @JsonProperty("opt")
    Integer isPublic;
    /**
     * 收藏夹id
     */
    @JsonProperty("folder")
    Long folderId;

    public FavorFolderModifyParam() {
        setAct("modify_folder");
        setLib("topic_favor_v2");
    }
}
