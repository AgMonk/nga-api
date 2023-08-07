package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.FollowStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

/**
 * 关注动态body
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/8/7 10:39
 **/
@Getter
@Setter
public class FollowStatusBody {
    @JsonAlias("0")
    LinkedHashMap<Integer, FollowStatus> data;
}
