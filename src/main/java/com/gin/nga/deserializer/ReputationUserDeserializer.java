package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.field.ReputationUser;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/28 14:35
 */
public class ReputationUserDeserializer extends JsonDeserializer<ReputationUser> {
    @Override
    public ReputationUser deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final TreeNode treeNode = p.getCodec().readTree(p);
        final Iterator<String> iterator = treeNode.fieldNames();
        if (iterator.hasNext()) {
            final String next = iterator.next();
            final TreeNode node = treeNode.get(next);

            final ReputationUser result = new ReputationUser();
            LinkedHashMap<Long, Serializable> map = NgaRes.MAPPER.readValue(node.toString(), new TypeReference<>() {
            });
            result.setId(Integer.valueOf(next));
            final Map<Long, Integer> data = result.getData();
            map.forEach((id, value) -> {
                if (id == 0) {
                    result.setName(String.valueOf(value));
                } else {
                    data.put(id, Integer.parseInt(String.valueOf(value)));
                }
            });
            return result;
        }
        return null;
    }
}
    