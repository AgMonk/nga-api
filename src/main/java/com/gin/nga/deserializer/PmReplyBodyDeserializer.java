package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.gin.common.utils.StrUtils;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.body.nuke.PmReplyBody;
import com.gin.nga.response.field.UserFieldInRead;
import com.gin.nga.response.field.pm.PrivateMessageReply;

import java.util.List;


/**
 * 私信内容反序列化
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 15:38
 */
public class PmReplyBodyDeserializer extends AbstractSingleListDeserializer<PmReplyBody, PrivateMessageReply> {
    @Override
    public PmReplyBody buildResult(List<PrivateMessageReply> list) {
        final PmReplyBody res = new PmReplyBody();
        res.setData(list);
        return res;
    }
    @Override
    public void handleItem(PmReplyBody result, List<PrivateMessageReply> list, String fieldName, TreeNode child) throws JsonProcessingException {
        final String childString = child.toString();
        if (StrUtils.isNumber(fieldName)) {
            // 数字 ，是私信
            list.add(NgaRes.MAPPER.readValue(childString, PrivateMessageReply.class));
        } else {
            //noinspection EnhancedSwitchMigration
            switch (fieldName) {
                case "length":
                    result.setLength(Integer.parseInt(childString));
                    break;
                case "nextPage":
                    result.setHasNext(Boolean.parseBoolean(childString));
                    break;
                case "currentPage":
                    result.setPage(Integer.parseInt(childString));
                    break;
                case "starterUid":
                    result.setStarterUid(Long.parseLong(childString));
                    break;
                case "subjectBit":
                    result.setBit(Integer.parseInt(childString));
                    break;
                case "allUsers":
                    result.setUsers(PmUsersDeserializer.parse(childString.replace("\"", "")));
                    break;
                case "userInfo":
                    result.setUserInfo(new UserFieldInRead(NgaRes.MAPPER.readValue(childString, new TypeReference<>() {
                    })));
                    break;
                default:
                    System.out.printf("未识别的字段: %s -> %s\n", fieldName, childString);
            }
        }
    }
}
    