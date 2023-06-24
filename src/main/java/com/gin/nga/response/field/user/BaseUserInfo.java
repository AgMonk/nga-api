package com.gin.nga.response.field.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.jackson.deserializer.ListIntDeserializer;
import com.gin.jackson.serializer.ZdtJsonSerializer;
import com.gin.jackson.utils.ObjectUtils;
import com.gin.nga.bbscode.entity.BbsTag;
import com.gin.nga.bbscode.utils.BbsTagParser;
import com.gin.nga.deserializer.UserBuffDeserializer;
import com.gin.nga.enums.AccountStatus;
import com.gin.nga.enums.UserBuffType;
import com.gin.nga.response.field.Honor;
import com.gin.nga.response.field.Money;
import com.gin.nga.response.field.SimpleUserInfo;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户信息的公共字段
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/17 14:07
 */
@Getter
@Setter
public class BaseUserInfo extends SimpleUserInfo {
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

    public List<BbsTag> getSignatureNodes() {
        return signature == null ? null : BbsTagParser.parseContent(signature);
    }

    public UserBuff getAvatarBuff(){
        if (ObjectUtils.isEmpty(this.buffs)){
            return null;
        }
        final List<UserBuff> buffs = this.buffs.stream().filter(i -> i.getType() == UserBuffType.AVATAR_CHANGED).collect(Collectors.toList());
        if (ObjectUtils.isEmpty(buffs)){
            return null;
        }
        return buffs.get(buffs.size()-1);
    }
}
