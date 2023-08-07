package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.response.field.SimpleUserInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

/**
 * 关注用户列表body
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/8/7 10:11
 **/
@Getter
@Setter
public class FollowUserListBody {
    @JsonAlias("0")
    LinkedHashMap<Integer, SimpleUserInfo> data;
}
