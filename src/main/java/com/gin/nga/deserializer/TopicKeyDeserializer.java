package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.body.nuke.TopicKey;
import com.gin.nga.response.body.nuke.TopicKeys;

import java.io.IOException;
import java.util.Iterator;


/**
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 14:23
 */
public class TopicKeyDeserializer extends JsonDeserializer<TopicKeys> {
    @Override
    public TopicKeys deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final TopicKeys res = new TopicKeys();
        final TreeNode treeNode = p.getCodec().readTree(p).get("0");

        final Iterator<String> iterator = treeNode.fieldNames();
        while (iterator.hasNext()){
            res.add(NgaRes.MAPPER.readValue(treeNode.get(iterator.next()).toString(), TopicKey.class));
        }

        return res;
    }
}
    