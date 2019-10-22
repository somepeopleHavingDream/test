package org.yangxin.test.leetcode;

/**
 * 一个有序数组中同一种数字只能出现一次
 *
 * @author yangxin
 * 2019/10/22 16:10
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
//        int[] nums = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int[] nums = new int[]{1, 3};
//        int[] nums = new int[]{1, 1, 3};
        int length = removeDuplicates(nums);

        for (int i = 0; i < length; i++) {
            System.out.print(nums[i]);
        }
    }

    private static int removeDuplicates(int[] nums) {
        // 只有满足条件，才前进
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            // 找到一个和自身不相等的值
            if (nums[j] != nums[i]) {
                nums[++i] = nums[j];
            }
        }

        return ++i;
    }
}
