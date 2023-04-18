package com.gin.nga.deserializer;

import com.fasterxml.jackson.core.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * nuke接口返回列表的反序列化方法
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/18 14:52
 */
public abstract class NukeListDeserializer<T> extends NukeDeserializer<ArrayList<T>> {
    @Override
    public final ArrayList<T> convert(TreeNode treeNode) {
        final ArrayList<T> list = new ArrayList<>();
        final Iterator<String> iterator = treeNode.fieldNames();
        while (iterator.hasNext()){
            list.add(convertItem(treeNode.get(iterator.next())));
        }
        return list;
    }
    /**
     * 将节点转换为响应类型
     * @param treeNode 树节点
     * @return 响应
     */
    public abstract T convertItem(TreeNode treeNode);
}
