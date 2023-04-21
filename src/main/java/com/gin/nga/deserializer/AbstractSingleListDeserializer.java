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
 * 只返回一个列表的数据的反序列化
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 10:13
 */
public abstract class AbstractSingleListDeserializer<T,I> extends JsonDeserializer<T> {
    @Override
    public final T deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        final TreeNode treeNode = p.getCodec().readTree(p);
        final TreeNode root = treeNode.get(treeNode.fieldNames().next());

        List<I> list = new ArrayList<>();
        final T result = buildResult(list);
        final Iterator<String> iterator = root.fieldNames();
        while (iterator.hasNext()){
            // 子节点
            final String fieldName = iterator.next();
            final TreeNode child = root.get(fieldName);
            // 将子节点转换为指定类型
            handleItem(result,list,fieldName,child);
        }

        return result;
    }

    /**
     * 解析每个子节点, 并放入返回对象中
     * @param result 返回对象
     * @param list 返回列表
     * @param fieldName 字段名
     * @param child 子节点
     * @author bx002
     * @since 2023/4/21 10:21
     * @throws JsonProcessingException 解析异常
     */
    public abstract void handleItem(T result, List<I> list, String fieldName, TreeNode child) throws JsonProcessingException;
    /**
     * 使用解析的列表创建返回对象
     * @param list 列表
     * @return 返回对象
     */
    public abstract T buildResult(List<I> list);

}
