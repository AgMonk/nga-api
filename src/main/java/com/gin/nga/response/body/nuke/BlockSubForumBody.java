package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.BlockSubForum;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/19 15:22
 */
@Getter
@Setter
public class BlockSubForumBody {
    @JsonAlias("0")
    BlockSubForum data;
}   
