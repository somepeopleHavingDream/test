package org.yangxin.test.leetcode;

/**
 * 翻转二叉树
 *
 * @author yangxin
 * 2020/01/10 11:36
 */
public class InvertBinaryTree {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    private static TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }

        TreeNode leftTree = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(leftTree);

        return root;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(9);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        node3.left = node6;
        node3.right = node7;

        TreeNode treeNode = invertTree(node1);
//        printTree(node1);
        printTree(treeNode);
    }

    private static void printTree(TreeNode treeNode) {
        // 先序遍历
        if (treeNode == null) {
            return;
        }

        System.out.print(treeNode.val);
        printTree(treeNode.left);
        printTree(treeNode.right);
    }
}
