package org.yangxin.test.leetcode;

/**
 * 平衡二叉树
 *
 * @author yangxin
 * 2019/11/08 09:36
 */
public class BalancedBinaryTree {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);
        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        TreeNode node6 = new TreeNode(1);
        TreeNode node7 = new TreeNode(2);
        TreeNode node8 = new TreeNode(2);
        TreeNode node9 = new TreeNode(3);
        TreeNode node10 = new TreeNode(3);
        TreeNode node11 = new TreeNode(4);
        TreeNode node12 = new TreeNode(4);
        node6.left = node7;
        node6.right = node8;
        node7.left = node9;
        node7.right = node10;
        node9.left = node11;
        node9.right = node12;

        boolean result = isBalanced(node6);
        System.out.println(result);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    private static boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }

        if (Math.abs(height(root.left) - height(root.right)) > 1) {
            return false;
        }

        return isBalanced(root.left) && isBalanced(root.right);
    }

    private static int height(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }

        if (treeNode.left == null && treeNode.right == null) {
            return 1;
        }

        return Math.max(height(treeNode.left), height(treeNode.right)) + 1;
    }
}
