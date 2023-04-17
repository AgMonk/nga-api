package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.common.deserializer.ListIntDeserializer;
import com.gin.nga.deserializer.UserAvatarDeserializer;
import com.gin.nga.response.field.Honor;
import com.gin.nga.response.field.Money;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * nuke.php接口返回的用户详细信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 10:36
 */
@Getter
@Setter
public class UserInfoBody {
    /**
     * 超管
     */
    @JsonProperty("_admin")
    boolean admin;
    /**
     * greater管理员
     */
    @JsonProperty("_greater")
    boolean greaterAdmin;
    /**
     * lesser管理员
     */
    @JsonProperty("_lesser")
    boolean lesserAdmin;
    /**
     * super管理员
     */
    @JsonProperty("_super")
    boolean superAdmin;
    /**
     * 头像
     */
    @JsonProperty("avatar")
    @JsonDeserialize(using = UserAvatarDeserializer.class)
    List<String> avatars;
    @JsonProperty("bit")
    Long bitData;
    @JsonProperty("email")
    String email;
    /**
     * todo 未知数据
     */
    @JsonProperty("faction")
    Object faction;
    /**
     * 威望
     */
    @JsonProperty("fame")
    Integer fame;
    /**
     * 关注数
     */
    @JsonProperty("follow")
    Integer followCount;
    /**
     * 被关注数
     */
    @JsonProperty("follow_by_num")
    Integer followedCount;
    /**
     * 用户组id(涉及管理权限)
     */
    @JsonProperty("gid")
    Long gid;
    /**
     * 威望组名
     */
    @JsonProperty("group")
    String groupName;
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
     * ip属地
     */
    @JsonProperty("ipLoc")
    String ipLocation;
    /**
     * todo 不知道是什么物品的数量
     */
    @JsonProperty("items")
    Integer items;
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
     * 威望
     */
    @JsonProperty("rvrc")
    Integer prestige;

}   
