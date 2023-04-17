package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.nga.deserializer.UserBuffDeserializer;
import com.gin.nga.deserializer.UserForumDeserializer;
import com.gin.nga.response.field.BaseUserInfo;
import com.gin.nga.response.field.UserBuff;
import com.gin.nga.response.field.UserMoreInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

/**
 * nuke.php接口返回的用户详细信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 10:36
 */
@Getter
@Setter
public class UserInfoBody extends  BaseUserInfo{
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
     * 持有管理权限的版面 版面id->版面名称
     */
    @JsonProperty("adminForums")
    LinkedHashMap<Long, String> adminForums;

    @JsonProperty("bit")
    Long bitData;
    @JsonProperty("buffs")
    @JsonDeserialize(using = UserBuffDeserializer.class)
    LinkedHashMap<Integer, UserBuff> buffs;
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
     * 更多信息
     */
    @JsonProperty("more_info")
    LinkedHashMap<Integer, UserMoreInfo> moreInfo;
    /**
     * 备注
     */
    @JsonProperty("remark")
    String remark;
    /**
     * 个人版名称
     */
    @JsonProperty("site")
    @JsonDeserialize(using = UserForumDeserializer.class)
    String userForum;

    public static class Res extends LinkedHashMap<Integer, UserInfoBody> {
    }

}   
