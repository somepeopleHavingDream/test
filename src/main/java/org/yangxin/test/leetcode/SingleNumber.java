package org.yangxin.test.leetcode;

/**
 * 找到只出现一次的元素
 *
 * @author yangxin
 * 2019/11/12 09:44
 */
public class SingleNumber {
    public static void main(String[] args) {
        int[] arr1 = {2, 2, 1};
        int[] arr2 = {4, 1, 2, 1, 2};
        int result = singleNumber(arr2);
        System.out.println(result);
    }

    private static int singleNumber(int[] nums) {
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    }
}
