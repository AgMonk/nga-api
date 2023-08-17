package com.gin.nga.response.field.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.nga.deserializer.UserForumDeserializer;
import com.gin.nga.deserializer.UserRemarkNukeDeserializer;
import com.gin.nga.enums.UserMoreInfoType;
import com.gin.nga.response.field.Reputation;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * nuke.php接口返回的用户详细信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 10:36
 */
@Getter
@Setter
public class UserInfoNuke extends BaseUserInfo {
    /**
     * 有管理员权限
     */
    @JsonAlias("_admin")
    boolean admin;
    /**
     * 有版主权限
     */
    @JsonAlias("_greater")
    boolean greaterAdmin;
    /**
     * 有次级版主权限
     */
    @JsonAlias("_lesser")
    boolean lesserAdmin;
    /**
     * 有超版权限
     */
    @JsonAlias("_super")
    boolean superAdmin;

    /**
     * 持有管理权限的版面 版面id->版面名称
     */
    @JsonAlias("adminForums")
    LinkedHashMap<Long, String> adminForums;
    /**
     * 位数据
     */
    @JsonAlias("bit")
    Long bitData;
    /**
     * 邮箱
     */
    @JsonAlias("email")
    String email;
    /**
     * todo 未知数据
     */
    @JsonAlias("faction")
    Object faction;
    /**
     * 威望
     */
    @JsonAlias("fame")
    Integer fame;
    /**
     * 关注数
     */
    @JsonAlias("follow")
    Integer followCount;
    /**
     * 被关注数
     */
    @JsonAlias("follow_by_num")
    Integer followedCount;
    /**
     * 用户组id(涉及管理权限)
     */
    @JsonAlias("gid")
    Long gid;
    /**
     * 威望组名
     */
    @JsonAlias("group")
    String groupName;
    /**
     * ip属地
     */
    @JsonAlias("ipLoc")
    String ipLocation;
    /**
     * todo 不知道是什么物品的数量
     */
    @JsonAlias("items")
    Integer items;
    /**
     * 更多信息
     */
    @JsonAlias("more_info")
    LinkedHashMap<Integer, UserMoreInfo> moreInfo;
    /**
     * 备注
     */
    @JsonAlias("remark")
    @JsonDeserialize(using = UserRemarkNukeDeserializer.class)
    List<UserRemarkNuke> remarks;
    /**
     * 个人版名称
     */
    @JsonAlias("site")
    @JsonDeserialize(using = UserForumDeserializer.class)
    String userForum;
    /**
     * 声望数据
     */
    @JsonAlias("reputation")
    LinkedHashMap<Integer, Reputation> reputations;

    /**
     * 总互动数
     * @return 总互动数
     */
    public Long getInteractions(){
        if (moreInfo==null){
            return null;
        }
        final UserMoreInfo info = moreInfo.values().stream().filter(i -> i.getType() == UserMoreInfoType.interactions).findFirst().orElse(null);
        if (info != null) {
            return info.getData();
        }
        return null;
    }
}
