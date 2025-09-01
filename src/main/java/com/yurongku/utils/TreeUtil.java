package com.yurongku.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 树形结构工具类
 */
public class TreeUtil {

    /**
     * 构建树形结构
     *
     * @param list 原始列表
     * @param <T>  实现了TreeNode接口的类型
     * @return 树形结构列表
     */
    public static <T extends TreeNode<T>> List<T> buildTree(List<T> list) {
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }

        // 按父ID分组
        Map<Long, List<T>> parentMap = list.stream()
                .collect(Collectors.groupingBy(item -> item.getParentId() == null ? 0L : item.getParentId()));

        // 获取根节点
        List<T> rootList = parentMap.get(0L);
        if (rootList == null) {
            return new ArrayList<>();
        }

        // 递归构建树
        rootList.forEach(root -> buildChildren(root, parentMap));

        return rootList;
    }

    /**
     * 递归构建子节点
     *
     * @param parent    父节点
     * @param parentMap 父ID映射
     * @param <T>       实现了TreeNode接口的类型
     */
    private static <T extends TreeNode<T>> void buildChildren(T parent, Map<Long, List<T>> parentMap) {
        List<T> children = parentMap.get(parent.getId());
        if (children != null) {
            parent.setChildren(children);
            children.forEach(child -> buildChildren(child, parentMap));
        }
    }

    /**
     * 树形节点接口
     *
     * @param <T> 节点类型
     */
    public interface TreeNode<T> {
        /**
         * 获取节点ID
         */
        Long getId();

        /**
         * 获取父节点ID
         */
        Long getParentId();

        /**
         * 设置子节点列表
         */
        void setChildren(List<T> children);
    }
}
