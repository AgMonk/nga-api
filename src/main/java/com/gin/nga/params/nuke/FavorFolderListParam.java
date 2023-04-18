package com.gin.nga.params.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 查询收藏夹列表参数
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 16:19
 */
@Getter
@Setter
public class FavorFolderListParam extends NukeBaseParam {
    Integer page;
    @JsonProperty("uid")
    Long userId;

    public FavorFolderListParam() {
        setAct("list_folder");
        setLib("topic_favor_v2");
    }
}
