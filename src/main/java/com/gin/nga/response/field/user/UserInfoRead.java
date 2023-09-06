package com.gin.nga.response.field.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.jackson.serializer.ZdtJsonSerializer;
import com.gin.nga.response.field.ReputationMap;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.LinkedHashMap;

/**
 * read.php接口返回的用户信息
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/13 13:31
 */
@Getter
@Setter
public class UserInfoRead extends BaseUserInfo {
    @JsonAlias("credit")
    Long credit;
    /**
     * 昵称
     */
    @JsonAlias("nickname")
    String nickname;
    /**
     * 声望数据 , 声望id -> 声望值
     */
    @JsonAlias("reputation")
    ReputationMap reputationMap;
    /**
     * 个人版名称
     */
    @JsonAlias("site")
    String userForum;
    /**
     * 最近访问
     */
    @JsonAlias("thisvisit")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime latestVisit;
    /**
     * 备注
     */
    @JsonAlias("remark")
    LinkedHashMap<Integer,UserRemarkRead> remarks;
}
