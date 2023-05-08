package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.forum.ForumGroup;
import com.gin.nga.response.forum.ForumGroupBody;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * 首页版面入口反序列化
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/5/8 11:53
 */
public class ForumGroupBodyDeserializer extends JsonDeserializer<ForumGroupBody> {
    @Override
    public ForumGroupBody deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final TreeNode root = p.getCodec().readTree(p);
        final ForumGroupBody body = new ForumGroupBody();
        final ArrayList<ForumGroup> groups = new ArrayList<>();
        body.setGroups(groups);
        try {
            final TreeNode all = root.get("data").get("0").get("all");

            final Iterator<String> iterator = all.fieldNames();
            while (iterator.hasNext()) {
                final String next = iterator.next();
                final TreeNode node = all.get(next);
                System.out.println("node = " + node);
                groups.add(NgaRes.MAPPER.readValue(node.toString(), ForumGroup.class));
            }
        } catch (NullPointerException e) {
            return null;
        }
        // 清理空组
        body.clear();
        return body;
    }
}
    