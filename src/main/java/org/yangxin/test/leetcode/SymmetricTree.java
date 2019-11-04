package org.yangxin.test.leetcode;

/**
 * 对称树
 *
 * @author yangxin
 * 2019/11/01 11:57
 */
public class SymmetricTree {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
//        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(3);
//        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(3);
        node1.left = node2;
        node1.right = node3;
        node2.left = null;
//        node2.left = node4;
        node2.right = node5;
        node3.left = null;
//        node3.left = node6;
        node3.right = node7;
        boolean result = isSymmetric(node1);
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

    private static boolean isSymmetric(TreeNode root) {
        return isMirror(root ,root);
    }

    private static boolean isMirror(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) {
            return true;
        }

        if (r1 != null && r2 != null && r1.val == r2.val) {
            return isMirror(r1.left, r2.right) && isMirror(r1.right, r2.left);
        }

        return false;
    }
}
