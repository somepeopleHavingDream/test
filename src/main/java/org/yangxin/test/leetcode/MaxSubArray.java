package org.yangxin.test.leetcode;

/**
 * 最大子数组和
 *
 * @author yangxin
 * 2019/10/23 10:07
 */
public class MaxSubArray {
    public static void main(String[] args) {
//        int[] nums = new int[]{-1};
//        int[] nums = new int[]{-1, -2};
        int[] nums = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int result = maxSubArray(nums);
        System.out.println(result);
    }

    private static int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int sum = 0;
        for (int num : nums) {
            sum += num;

            if (sum < 0) {
                sum = 0;
                maxSum = Math.max(maxSum, num);
                continue;
            }

            maxSum = Math.max(maxSum, sum);
        }

        return maxSum;

//        int res = nums[0];
//        int sum = 0;
//        for (int num : nums) {
//            if (sum > 0)
//                sum += num;
//            else
//                sum = num;
//            res = Math.max(res, sum);
//        }
//        return res;
    }
}
