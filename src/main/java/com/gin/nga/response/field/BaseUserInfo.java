package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.deserializer.ListIntDeserializer;
import com.gin.common.serializer.ZdtJsonSerializer;
import com.gin.nga.deserializer.UserAvatarDeserializer;
import com.gin.nga.enums.AccountStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * 用户信息的公共字段
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 14:07
 */
@Getter
@Setter
public class BaseUserInfo {
    /**
     * 头像
     */
    @JsonProperty("avatar")
    @JsonDeserialize(using = UserAvatarDeserializer.class)
    List<String> avatars;
    /**
     * 用户组id(涉及管理权限)
     */
    @JsonProperty("groupid")
    Long groupId;
    /**
     * 荣誉称号
     */
    @JsonProperty("honor")
    Honor honor;
    /**
     * 徽章id
     */
    @JsonProperty("medal")
    @JsonDeserialize(using = ListIntDeserializer.class)
    List<Integer> medalIds;
    /**
     * 威望组ID
     */
    @JsonProperty("memberid")
    Integer memberId;
    /**
     * 货币
     */
    @JsonProperty("money")
    Money money;
    /**
     * 注册时间
     */
    @JsonProperty("regdate")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime regDatetime;
    /**
     * 发帖数量
     */
    @JsonProperty("posts")
    @JsonAlias("postnum")
    Long postCount;
    /**
     * 威望
     */
    @JsonProperty("rvrc")
    Integer prestige;

    /**
     * 签名
     */
    @JsonProperty("sign")
    @JsonAlias("signature")
    String signature;
    /**
     * 用户id
     */
    @JsonProperty("uid")
    Long userId;
    /**
     * 用户名
     */
    @JsonProperty("username")
    String username;
    /**
     * 账号状态
     */
    @JsonProperty("yz")
    AccountStatus accountStatus;
}
