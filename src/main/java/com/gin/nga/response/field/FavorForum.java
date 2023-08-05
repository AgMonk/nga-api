package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.utils.ForumIconUtils;
import lombok.Getter;
import lombok.Setter;

/**
 * 收藏的版面或合集
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 14:47
 */
@Getter
@Setter
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

    public String getIconUrl(){
        return ForumIconUtils.getIconUrl(forumId, colTid);
    }

    public String getBigIconUrl(){
        return ForumIconUtils.getBigIconUrl(forumId, colTid);
    }

}
