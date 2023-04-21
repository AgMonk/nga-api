package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.common.utils.StrUtils;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.body.nuke.PmReplyBody;
import com.gin.nga.response.field.UserFieldInRead;
import com.gin.nga.response.field.pm.PrivateMessageReply;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * 私信内容反序列化
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/20 15:38
 */
public class PmReplyBodyDeserializer extends JsonDeserializer<PmReplyBody> {
    @Override
    public PmReplyBody deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final TreeNode treeNode = p.getCodec().readTree(p);
        final TreeNode root = treeNode.get(treeNode.fieldNames().next());
        final PmReplyBody res = new PmReplyBody();
        final ArrayList<PrivateMessageReply> data = new ArrayList<>();
        res.setData(data);

        final Iterator<String> iterator = root.fieldNames();
        while (iterator.hasNext()) {
            final String fieldName = iterator.next();
            final TreeNode node = root.get(fieldName);
            if (StrUtils.isNumber(fieldName)) {
                // 数字 ，是私信
                data.add(NgaRes.MAPPER.readValue(node.toString(), PrivateMessageReply.class));
            }else{
                //noinspection EnhancedSwitchMigration
                switch (fieldName){
                    case "length":res.setLength(Integer.parseInt(node.toString()));break;
                    case "nextPage":res.setHasNext(Boolean.parseBoolean(node.toString())); break;
                    case "currentPage":res.setPage(Integer.parseInt(node.toString()));break;
                    case "starterUid":res.setStarterUid(Long.parseLong(node.toString()));break;
                    case "subjectBit":res.setBit(Integer.parseInt(node.toString()));break;
                    case "allUsers":
                        res.setUsers(PmUsersDeserializer.parse(node.toString().replace("\"", "")));
                        break;
                    case "userInfo":res.setUserInfo(new UserFieldInRead(NgaRes.MAPPER.readValue(node.toString(), new TypeReference<>() {})));break;
                    default:
                        System.out.printf("未识别的字段: %s -> %s\n",fieldName,node.toString());
                }
            }
        }
        return res;
    }
}
    