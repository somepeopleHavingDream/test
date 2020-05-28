package org.yangxin.test.leetcode;

import java.util.Arrays;

/**
 * @author yangxin
 * 2020/05/28 15:43
 */
public class MissingNumber {

    public static void main(String[] args) {
        int[] nums = {3, 0, 1};
//        int[] nums = {0};
//        int[] nums = {9, 6, 4, 2, 3, 5, 7, 0, 1};
        System.out.println(missingNumberByXOR(nums));
//        System.out.println(missingNumber(nums));
    }

    private static int missingNumber(int[] nums) {
        // 先升序
        Arrays.sort(nums);

        int i = 0;
        for (; i < nums.length; i++) {
            if (i != nums[i]) {
                return i;
            }
        }

        return i;
    }

    private static int missingNumberByXOR(int[] nums) {
//        int missingNum = nums.length;
        int missingNum = 0;
        for (int i = 0; i < nums.length; i++) {
            missingNum ^= nums[i];
            missingNum ^= i;
        }

        return missingNum;
    }
}
