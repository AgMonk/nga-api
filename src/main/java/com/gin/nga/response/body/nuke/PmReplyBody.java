package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.nga.enums.PrivateMessageStatus;
import com.gin.nga.response.field.SimpleUserInfo;
import com.gin.nga.response.field.pm.PrivateMessageReply;
import com.gin.nga.response.field.user.UserContext;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.List;

/**
 * 私信回复body
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 15:29
 */
@Getter
@Setter
public class PmReplyBody {

    @JsonAlias("0")
    Data data;

    @Getter
    @Setter
    public static class Data{
        /**
         * 私信回复
         */
        @JsonAlias("allmsgs")
        LinkedHashMap<Integer,PrivateMessageReply> data;
        /**
         * 长度
         */
        int length;
        /**
         * 是否还有下一页
         */
        @JsonAlias("nextPage")
        String hasNext;
        /**
         * 发起私信的用户uid
         */
        Long starterUid;
        /**
         * 所有参与的用户
         */
        List<SimpleUserInfo> users;
        /**
         * 当前页
         */
        @JsonAlias("currentPage")
        int page;
        /**
         * 用户信息
         */
        @JsonAlias("userInfo")
        UserContext userContext;
        /**
         * bit
         */
        @JsonAlias("subjectBit")
        int bit;
        /**
         * 状态
         * @return 状态
         */
        public List<PrivateMessageStatus> getStatus() {
            return PrivateMessageStatus.parseStatus(bit);
        }
    }
}
