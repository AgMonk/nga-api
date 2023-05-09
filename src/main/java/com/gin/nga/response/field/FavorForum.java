package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * 收藏的版面或合集
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 14:47
 */
public class FavorForum {
    /**
     * 版面id
     */
    @JsonAlias("fid")
    Long forumId;
    /**
     * id
     */
    @JsonAlias("id")
    Long id;
    /**
     * 信息
     */
    @JsonAlias("info")
    String info;
    /**
     * 版面或合集名称
     */
    @JsonAlias("name")
    String name;
    /**
     * 合集Tid
     */
    @JsonAlias("stid")
    Long colTid;

    public String getIconUrl() {
        if (this.id==null){
            return null;
        }
        if (colTid!=null){
            return String.format(ForumInfo.ICON_FORMAT_COL,id);
        }
        return String.format(ForumInfo.ICON_FORMAT, id);
    }

}
