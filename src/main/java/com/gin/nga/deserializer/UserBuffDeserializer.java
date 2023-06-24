package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.field.user.UserBuff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 用户buff
 *
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 15:14
 */
public class UserBuffDeserializer extends JsonDeserializer<List<UserBuff>> {

    /**
     * 判断一个节点是否为buff数据节点
     *
     * @param node 节点
     * @return 是否为buff数据
     */
    private static boolean isBuff(TreeNode node) {
        return node.get(node.fieldNames().next()).isValueNode();
    }

    @Override
    public List<UserBuff> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final ArrayList<UserBuff> res = new ArrayList<>();
        final TreeNode root = p.getCodec().readTree(p);
        final Iterator<String> iterator = root.fieldNames();
        while (iterator.hasNext()) {
            final String key = iterator.next();
            final TreeNode child = root.get(key);
            if (isBuff(child)) {
                res.add(NgaRes.MAPPER.readValue(child.toString(), UserBuff.class));
            }else{
                final Iterator<String> it = child.fieldNames();
                while (it.hasNext()){
                    res.add(NgaRes.MAPPER.readValue(child.get(it.next()).toString(), UserBuff.class));
                }
            }
        }
        return res;
    }

}
    