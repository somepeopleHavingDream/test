package org.yangxin.test.leetcode;

/**
 * 移除数组中的给定值的元素
 *
 * @author yangxin
 * 2019/10/22 17:58
 */
public class RemoveElement {
    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
//        int[] nums = new int[]{3, 2, 2, 3};
        int val = 3;

        int result = removeElement(nums, val);
        for (int i = 0; i < result; i++) {
            System.out.print(nums[i]);
        }
    }

    private static int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != val) {
                nums[i++] = nums[j];
            }
        }
        return i;
    }
}
