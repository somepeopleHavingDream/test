package org.yangxin.test.leetcode;

import java.util.Arrays;

/**
 * 数组中是否有重复的元素
 *
 * @author yangxin
 * 2020/01/07 15:50
 */
public class ContainsDuplicate {
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 3, 3, 4, 3, 2, 4, 2};
//        int[] nums = {1, 2, 3, 4};
//        int[] nums = {1, 2, 3, 1};
        boolean result = containsDuplicate(nums);
        System.out.println(result);
    }

    private static boolean containsDuplicate(int[] nums) {
        // 如果该数组只有0个或者1个元素，则该数组没有重复的元素
        if (nums.length == 0 || nums.length == 1) {
            return false;
        }

        // 让数组升序
        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }

        return false;
    }
}
