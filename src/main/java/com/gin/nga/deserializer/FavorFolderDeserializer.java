package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.field.FavorFolder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * 收藏夹反序列化
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 16:24
 */
public class FavorFolderDeserializer extends JsonDeserializer<List<FavorFolder>> {
    @Override
    public List<FavorFolder> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final ArrayList<FavorFolder> res = new ArrayList<>();
        final TreeNode root = p.getCodec().readTree(p);
        final Iterator<String> iterator = root.fieldNames();
        while (iterator.hasNext()){
            final TreeNode node = root.get(iterator.next());
            res.add(NgaRes.MAPPER.readValue(node.toString(),FavorFolder.class));
        }

        return res;
    }
}
    