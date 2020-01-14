package org.yangxin.test.leetcode;

/**
 * 在二分查找树中找到给定两个节点的最短公共祖先
 *
 * @author yangxin
 * 2020/01/14 10:59
 */
public class LowestCommonAncestorOfABinarySearchTree {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    private static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root.val < p.val && root.val < q.val) {
            return lowestCommonAncestor(root.right, p, q);
        }
        if (root.val > p.val && root.val > q.val) {
            return lowestCommonAncestor(root.left, p, q);
        }

        // 直接返回就行，不会有第二个祖先处在这个范围内
        return root;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(8);
        TreeNode node4 = new TreeNode(0);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(9);
        TreeNode node8 = new TreeNode(3);
        TreeNode node9 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;
        node5.left = node8;
        node6.right = node9;

//        TreeNode treeNode = lowestCommonAncestor(node1, node2, node3);
        TreeNode treeNode = lowestCommonAncestor(node1, node2, node5);
        System.out.println(treeNode == null ? null : treeNode.val);
    }
}
