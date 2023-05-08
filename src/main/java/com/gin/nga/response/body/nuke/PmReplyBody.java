package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.gin.nga.deserializer.PmReplyBodyDeserializer;
import com.gin.nga.enums.PrivateMessageStatus;
import com.gin.nga.response.field.SimpleUserInfo;
import com.gin.nga.response.field.UserContext;
import com.gin.nga.response.field.pm.PrivateMessageReply;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 私信回复body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 15:29
 */
@Getter
@Setter
@JsonDeserialize(using = PmReplyBodyDeserializer.class)
public class PmReplyBody {
    /**
     * 私信回复
     */
    List<PrivateMessageReply> data;
    /**
     * 长度
     */
    int length;
    /**
     * 是否还有下一页
     */
    boolean hasNext;
    /**
     * 发起私信的用户uid
     */
    long starterUid;
    /**
     * 所有参与的用户
     */
    List<SimpleUserInfo> users;
    /**
     * 当前页
     */
    int page;
    /**
     * 用户信息
     */
    UserContext userInfo;
    /**
     * bit
     */
    int bit;
    /**
     * 状态
     * @return 状态
     */
    public List<PrivateMessageStatus> getStatus() {
        return PrivateMessageStatus.parse(bit);
    }
}
