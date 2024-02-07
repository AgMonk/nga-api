package com.gin.nga.response.body.nuke;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.gin.jackson.utils.ObjectUtils;
import com.gin.nga.deserializer.PmUsersDeserializer;
import com.gin.nga.enums.PrivateMessageStatus;
import com.gin.nga.response.field.SimpleUserInfo;
import com.gin.nga.response.field.pm.PrivateMessageReply;
import com.gin.nga.response.field.user.UserContext;
import com.gin.nga.response.field.user.UserInfoRead;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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

        public Long getStarterUid() {
            final PrivateMessageReply firstReply = data.get(0);
            if (firstReply==null) {
                return starterUid;
            }
            return firstReply.getData()!=null?firstReply.getUserId():starterUid;
        }

        /**
         * 所有参与的用户
         * @return 所有参与的用户
         */
        public List<SimpleUserInfo> getUsers(){
            final List<SimpleUserInfo> users = findUsers(data);
            if (users!=null) {
                return users;
            }

            if (userContext!=null) {
                final Map<Long, UserInfoRead> userInfo = userContext.getUserInfo();
                if (userInfo!=null) {
                    return new ArrayList<>(userInfo.values());
                }
            }
            return null;
        }

        private static List<SimpleUserInfo> findUsers( LinkedHashMap<Integer,PrivateMessageReply> data){
            if (data==null) {
                return null;
            }
            final PrivateMessageReply firstReply = data.get(0);
            if (firstReply!=null) {
                return null;
            }
            final LinkedHashMap<Integer, String> map = firstReply.getData();
            if (map==null) {
                return null;
            }
            final String s = map.get(2);
            if (ObjectUtils.isEmpty(s)) {
                return null;
            }
            return PmUsersDeserializer.parse(s.replace(" ","||"));
        }
    }
}
