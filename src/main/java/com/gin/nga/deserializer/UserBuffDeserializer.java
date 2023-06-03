package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.field.user.UserBuff;
import com.gin.nga.utils.TreeNodeUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 用户buff
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 15:14
 */
public class UserBuffDeserializer extends JsonDeserializer<List<UserBuff>> {

    @Override
    public List<UserBuff> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final ArrayList<UserBuff> res = new ArrayList<>();
        final TreeNode root = p.getCodec().readTree(p);
        final Iterator<String> iterator = root.fieldNames();
        while(iterator.hasNext()){
            final String key = iterator.next();
            final TreeNode node = TreeNodeUtils.findLeaf(root.get(key));
            res.add(NgaRes.MAPPER.readValue(node.toString(), UserBuff.class));
        }
        return res;
    }

}
    