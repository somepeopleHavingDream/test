package org.yangxin.test.leetcode;

/**
 * 将有序数组转换成二叉搜索平衡树
 *
 * @author yangxin
 * 2019/11/05 10:21
 */
public class ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args) {
        int[] nums = {-10, -3, 0, 5, 9};
        TreeNode result = sortedArrayToBST(nums);

        printTree(result);
    }

    /**
     * 先序打印树
     */
    private static void printTree(TreeNode result) {
        if (result != null) {
            System.out.println(result.val);

            printTree(result.left);
            printTree(result.right);
        }
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }

    private static TreeNode sortedArrayToBST(int[] nums) {
        return nums == null ? null : buildBinarySearchTree(nums, 0, nums.length - 1);
    }

    private static TreeNode buildBinarySearchTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;
        TreeNode treeNode = new TreeNode(nums[mid]);
        treeNode.left = buildBinarySearchTree(nums, start, mid - 1);
        treeNode.right = buildBinarySearchTree(nums, mid + 1, end);

        return treeNode;
    }
}
