package com.gin.nga.utils;

import com.fasterxml.jackson.core.TreeNode;

/**
 * 工具类
 * @author : ginstone
 * @version : v1.0.0
 * @since : 2023/4/21 11:56
 */
public class TreeNodeUtils {

    /**
     * 返回当前节点的第一个子节点
     * @param root 根节点
     * @return 第一个子节点
     */
    public static TreeNode findFirstChild(TreeNode root) {
        return root.size() > 0 ? root.get(root.fieldNames().next()) : null;
    }

    /**
     * 如果当前节点只有一个字段，进入到该字段，否则原样返回
     * @param root 根节点
     * @return 最终节点
     */
    public static TreeNode findLeaf(TreeNode root) {
        if (root.size() == 1) {
            return findLeaf(root.get(root.fieldNames().next()));
        }
        return root;
    }

}
