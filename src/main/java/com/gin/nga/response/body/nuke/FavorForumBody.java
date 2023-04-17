package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gin.nga.response.field.ForumInfo;

import java.util.LinkedHashMap;

/**
 * 收藏的版面或合集
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 14:47
 */
public class FavorForumBody {
    /**
     * 版面id
     */
    @JsonProperty("fid")
    Long forumId;
    /**
     * id
     */
    @JsonProperty("id")
    Long id;
    /**
     * 信息
     */
    @JsonProperty("info")
    String info;
    /**
     * 版面或合集名称
     */
    @JsonProperty("name")
    String name;
    /**
     * 合集Tid
     */
    @JsonProperty("stid")
    Long colTid;

    public String getIconUrl() {
        return this.id == null ? null : String.format(ForumInfo.ICON_FORMAT, id);
    }

    public static class Res extends LinkedHashMap<Integer, LinkedHashMap<Integer, FavorForumBody>> {
    }
}   
