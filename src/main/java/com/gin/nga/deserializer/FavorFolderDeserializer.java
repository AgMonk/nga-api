package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.gin.nga.response.NgaRes;
import com.gin.nga.response.field.FavorFolder;


/**
 * 收藏夹反序列化
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 16:24
 */
public class FavorFolderDeserializer extends AbstractListDeserializer<FavorFolder> {
    @Override
    public FavorFolder convert(TreeNode treeNode) throws JsonProcessingException {
        return NgaRes.MAPPER.readValue(treeNode.toString(), FavorFolder.class);
    }
}
    