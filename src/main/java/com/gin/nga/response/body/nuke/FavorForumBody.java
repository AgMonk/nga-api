package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.FavorForum;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 15:25
 */
@Getter
@Setter
public class FavorForumBody {
    @JsonAlias("0")
    LinkedHashMap<Integer, FavorForum> data;
}   
