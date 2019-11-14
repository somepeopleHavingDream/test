package org.yangxin.test.leetcode;

import java.util.Arrays;

/**
 * 找到大多数的元素
 *
 * @author yangxin
 * 2019/11/14 09:33
 */
public class MajorityElement {
    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1, 1, 2, 2};
//        int[] nums = {3, 2, 3};
        int result = majorityElement(nums);
        System.out.println(result);
    }

    private static int majorityElement(int[] nums) {
        // 先升序
        Arrays.sort(nums);

        int i = 0;
        while (i < nums.length / 2) {
            i++;
        }

        return nums[i];
    }
}
