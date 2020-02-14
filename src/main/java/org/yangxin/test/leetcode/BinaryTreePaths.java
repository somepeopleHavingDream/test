package org.yangxin.test.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 二叉树的路径
 *
 * @author yangxin
 * 2020/01/15
 */
public class BinaryTreePaths {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    public static List<String> binaryTreePaths(TreeNode root) {
        // 找到所有的路径，把路径放入到集合中
        List<String> list = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        findPaths(list, root, builder);
        return list;
    }

    private static void findPaths(List<String> list, TreeNode root, StringBuilder builder) {
        // 将自己本身加入到路径中
        builder.append(root.val).append("->");

        if (root.left == null && root.right == null) {
            // 到底了，添加这一条路径
            int length = builder.length();
            list.add(builder.delete(length - 2, length).toString());

            // 清除builder
            return;
        }

        if (root.left != null) {
            findPaths(list, root.left, builder);
        }
        if (root.right != null) {
            findPaths(list, root.right, builder);
        }
    }

    /**
     * 深度优先搜索
     */
    private static void dps(TreeNode node1) {
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.right = node4;
        System.out.println(binaryTreePaths(node1));
    }
}
