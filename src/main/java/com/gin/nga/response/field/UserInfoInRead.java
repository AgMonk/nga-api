package com.gin.nga.response.field;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.deserializer.ListIntDeserializer;
import com.gin.common.serializer.ZdtJsonSerializer;
import com.gin.nga.deserializer.ReputationDeserializer;
import com.gin.nga.deserializer.UserAvatarDeserializer;
import com.gin.nga.enums.AccountStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * read.php接口返回的用户信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 13:31
 */
@Getter
@Setter
public class UserInfoInRead {
    @JsonProperty("avatar")
    @JsonDeserialize(using = UserAvatarDeserializer.class)
    List<String> avatars;

    @JsonProperty("bit_data")
    Long bitData;
    @JsonProperty("credit")
    Long credit;
    /**
     * 用户组id(涉及管理权限)
     */
    @JsonProperty("groupid")
    Long groupId;
    /**
     * 荣誉称号
     */
    @JsonProperty("honor")
    String honor;
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
     * 铜币数量
     */
    @JsonProperty("money")
    Long money;
    /**
     * 昵称
     */
    @JsonProperty("nickname")
    String nickname;
    /**
     * 发帖数量
     */
    @JsonProperty("postnum")
    Long postCount;
    /**
     * 注册时间
     */
    @JsonProperty("regdate")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime regDatetime;
    /**
     * 声望数据 , 声望id -> 声望值
     */
    @JsonProperty("reputation")
    @JsonDeserialize(using = ReputationDeserializer.class)
    LinkedHashMap<Integer, Integer> reputation;
    /**
     * 威望
     */
    @JsonProperty("rvrc")
    Integer prestige;
    /**
     * 签名
     */
    @JsonProperty("signature")
    String signature;
    /**
     * 未知 todo
     */
    @JsonProperty("site")
    Object site;
    /**
     * 最近访问
     */
    @JsonProperty("thisvisit")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime latestVisit;
    /**
     * 用户id
     */
    @JsonProperty("uid")
    Long userid;
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
