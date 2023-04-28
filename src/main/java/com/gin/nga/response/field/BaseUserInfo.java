package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.deserializer.ListIntDeserializer;
import com.gin.common.serializer.ZdtJsonSerializer;
import com.gin.nga.deserializer.UserBuffDeserializer;
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
public class BaseUserInfo extends SimpleUserInfo{
    /**
     * 用户buff
     */
    @JsonAlias("buffs")
    @JsonDeserialize(using = UserBuffDeserializer.class)
    List<UserBuff> buffs;

    /**
     * 用户组id(涉及管理权限)
     */
    @JsonAlias("groupid")
    Long groupId;
    /**
     * 荣誉称号
     */
    @JsonAlias("honor")
    Honor honor;
    /**
     * 徽章id
     */
    @JsonAlias("medal")
    @JsonDeserialize(using = ListIntDeserializer.class)
    List<Integer> medalIds;
    /**
     * 威望组ID
     */
    @JsonAlias("memberid")
    Integer memberId;
    /**
     * 货币
     */
    @JsonAlias("money")
    Money money;
    /**
     * 注册时间
     */
    @JsonAlias("regdate")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime regDatetime;
    /**
     * 发帖数量
     */
    @JsonAlias({"posts","postnum"})
    Long postCount;
    /**
     * 威望
     */
    @JsonAlias("rvrc")
    Integer prestige;
    /**
     * 签名
     */
    @JsonAlias({"sign","signature"})
    String signature;
    /**
     * 账号状态
     */
    @JsonAlias("yz")
    AccountStatus accountStatus;
}
