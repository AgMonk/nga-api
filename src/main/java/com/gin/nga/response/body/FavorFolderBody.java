package com.gin.nga.response.body;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.nga.deserializer.FavorFolderDeserializer;
import com.gin.nga.response.field.FavorFolder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 收藏夹
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 16:27
 */
@Getter
@Setter
public class FavorFolderBody {
    @JsonAlias("0")
    @JsonDeserialize(using = FavorFolderDeserializer.class)
    List<FavorFolder> data;
}   
