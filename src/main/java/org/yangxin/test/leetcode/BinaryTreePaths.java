package org.yangxin.test.leetcode;

import java.util.ArrayList;
import java.util.Collections;
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
        // 如果根节点为null，则直接返回
        if (root == null) {
            return Collections.emptyList();
        }

        List<String> resultList = new ArrayList<>();
        getResultList(root, "", resultList);
        return resultList;
    }

    private static void getResultList(TreeNode root, String line, List<String> resultList) {
        // 如果当前节点为null，则直接返回
        if (root == null) {
            return;
        }

        // 当前节点的值不为null，则将当前节点的值添加进来
        line += root.val;

        // 如果当前节点是叶子节点
        if (root.left == null && root.right == null) {
            resultList.add(line);
            return;
        }

        // 当前节点是非叶子节点，对于非叶子来说，肯定有一条路径通过更深层的非叶子节点
        if (root.left != null) {
            getResultList(root.left, line + "->", resultList);
        }
        if (root.right != null) {
            getResultList(root.right, line + "->", resultList);
        }
    }


    private static void solve(TreeNode root, String cur, List<String> ret) {
        if (root == null) {
            return;
        }

        cur += root.val;
        if (root.left == null && root.right == null) {
            ret.add(cur);
        } else {
            solve(root.left, cur + "->", ret);
            solve(root.right, cur + "->", ret);
        }
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
