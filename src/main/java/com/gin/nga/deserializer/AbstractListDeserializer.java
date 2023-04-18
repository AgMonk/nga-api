package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 列表的反序列化方法
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 14:52
 */
public abstract class AbstractListDeserializer<T> extends JsonDeserializer<List<T>> {
    @Override
    public List<T> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        //根节点
        final TreeNode root = p.getCodec().readTree(p);
        final List<T> list = new ArrayList<>();
        final Iterator<String> iterator = root.fieldNames();
        while (iterator.hasNext()){
            // 子节点
            final TreeNode child = root.get(iterator.next());
            // 将子节点转换为指定类型
            list.add(convert(child));
        }
        return list;
    }

    /**
     * 将节点转换为响应类型
     * @param treeNode 树节点
     * @return 响应
     */
    public abstract T convert(TreeNode treeNode) throws JsonProcessingException;
}
