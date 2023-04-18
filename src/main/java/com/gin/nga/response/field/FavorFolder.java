package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * 收藏夹
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 16:11
 */
@Getter
@Setter
public class FavorFolder {
    @JsonAlias("id")
    Long id;
    /**
     * 类型
     */
    @JsonAlias("type")
    Long type;
    /**
     * 收藏夹名称
     */
    @JsonAlias("name")
    String name;
    @JsonAlias("bytes")
    Integer bytes;
    /**
     * 收藏数
     */
    @JsonAlias("length")
    Integer length;
    /**
     * 是否为默认收藏夹
     */
    @JsonAlias("default")
    boolean defaultFolder;


}   
