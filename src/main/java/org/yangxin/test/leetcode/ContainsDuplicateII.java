package org.yangxin.test.leetcode;

/**
 * 存在重复元素2
 *
 * @author yangxin
 * 2020/01/07 15:57
 */
public class ContainsDuplicateII {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 2, 3};
//        int[] nums = {1, 0, 1, 1};
//        int[] nums = {99, 99};
//        int[] nums = {1, 2, 3, 1};
//        int k = 2;
//        int k = 1;
        int k = 2;
//        int k = 3;
        boolean result = containsNearbyDuplicate(nums, k);
        System.out.println(result);
    }

    private static boolean containsNearbyDuplicate(int[] nums, int k) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= i + k; j++) {
                if (j < nums.length && nums[i] == nums[j]) {
                    return true;
                }
            }
        }

        return false;
    }
}
