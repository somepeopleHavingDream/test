package org.yangxin.test.tree;

/**
 * 不用递归，不用栈，遍历二叉树
 */
public class TraversalTreeNoStackNoRecursion {
    public static void main(String[] args) {

    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 中序遍历
     */
    private static void inorderMorrisTraversal(TreeNode root) {
        TreeNode cur = root, prev;
        while (cur != null) {
            if (cur.left == null) {
                System.out.println(cur.val);
                cur = cur.right;
            } else {
                // find predecessor
                prev = cur.left;
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }

                // 2.a)
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    // 2.b)
                    prev.right = null;
                    System.out.println(cur.val);
                    cur = cur.right;
                }
            }
        }
    }

    /**
     * 前序遍历
     */
    private static void preorderMorrisTraversal(TreeNode root) {
        TreeNode cur = root, prev;
        while (cur != null) {
            if (cur.left == null) {
                System.out.println(cur.val);
                cur = cur.right;
            } else {
                // find predecessor
                prev = cur.left;
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }

                // 2.a)
                if (prev.right == null) {
                    System.out.println(cur);

                    prev.right = cur;
                    cur = cur.left;
                } else {
                    // 2.b)
                    prev.right = null;
                    System.out.println(cur.val);
                    cur = cur.right;
                }
            }
        }
    }
}
