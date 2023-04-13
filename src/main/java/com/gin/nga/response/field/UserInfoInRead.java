package com.gin.nga.response.field;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.nga.deserializer.UserInfoInReadDeserializer;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

/**
 * read.php 接口中的用户信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 10:58
 */
@Getter
@Setter
@JsonDeserialize(using = UserInfoInReadDeserializer.class)
public class UserInfoInRead {
    //todo map泛型

    /**
     * 常规用户信息 uid->info
     */
    Map<Long,Object> userInfo;
    /**
     * 匿名用户信息 临时id->info
     */
    Map<Integer,Object> anonymousUserInfo;
    /**
     * 用户组信息 id->info
     */
    Map<Integer,Object> groups;
    /**
     * 徽章信息 id->info
     */
    Map<Integer,Object> medals;
    /**
     * 声望信息 id -> uid -> 声望
     */
    Map<Long,Map<Long, Serializable>> reputations;

}
