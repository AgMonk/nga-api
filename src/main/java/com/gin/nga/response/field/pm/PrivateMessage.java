package com.gin.nga.response.field.pm;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.gin.common.serializer.ZdtJsonSerializer;
import com.gin.nga.deserializer.PmUsersDeserializer;
import com.gin.nga.enums.PrivateMessageStatus;
import com.gin.nga.response.field.SimpleUserInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.util.CollectionUtils;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * 私信
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 13:42
 */
@Getter
@Setter
public class PrivateMessage {
    @JsonAlias("mid")
    Long messageId;

    int bit;
    /**
     * 标题
     */
    @JsonAlias("subject")
    String title;
    /**
     * 发起时间
     */
    @JsonAlias("time")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime timeCreated;
    /**
     * 最后更新时间
     */
    @JsonAlias("last_modify")
    @JsonSerialize(using = ZdtJsonSerializer.class)
    ZonedDateTime lastModify;
    /**
     * 回复数
     */
    @JsonAlias("posts")
    Integer postCount;
    /**
     * 发起人uid
     */
    @JsonAlias("from")
    Long fromUid;
    /**
     * 发起人用户名
     */
    @JsonAlias("from_username")
    String fromUsername;
    /**
     * 最后回复的uid
     */
    @JsonAlias("last_from")
    Long lastReplyUid;
    /**
     * 最后回复的用户名
     */
    @JsonAlias("last_from_username")
    String lastReplyUsername;
    /**
     * 所有参与的用户
     */
    @JsonAlias("all_user")
    @JsonDeserialize(using = PmUsersDeserializer.class)
    List<SimpleUserInfo> users;

    /**
     * 状态
     * @return 状态
     */
    public List<PrivateMessageStatus> getStatus() {
        return PrivateMessageStatus.parse(bit);
    }

    /**
     * 是否未读
     * @return 是否未读
     */
    public boolean isUnread() {
        final List<PrivateMessageStatus> status = getStatus();
        if (CollectionUtils.isEmpty(status)){
            return false;
        }
        return status.contains(PrivateMessageStatus.UNREAD);
    }
}
