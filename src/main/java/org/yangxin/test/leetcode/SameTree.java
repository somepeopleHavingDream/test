package org.yangxin.test.leetcode;

/**
 * 相同树
 *
 * @author yangxin
 * 2019/10/30 10:16
 */
public class SameTree {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(2);
//        TreeNode node1 = new TreeNode(1);
//        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(4);
//        TreeNode node3 = new TreeNode(3);
        node1.left = null;
        node1.right = node3;

        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(3);
//        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(4);
//        TreeNode node6 = new TreeNode(3);
        node4.left = node5;
        node4.right = node6;

        boolean result = isSameTree(node1, node4);
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

    private static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p != null && q != null && p.val != q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else {
            return false;
        }
    }
}
