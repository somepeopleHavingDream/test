package org.yangxin.test.leetcode;

/**
 * 家庭小偷，不能偷相邻的两家房子
 *
 * @author yangxin
 * 2019/11/28 14:12
 */
public class HouseRobber {
    public static void main(String[] args) {
        int[] nums = {1, 3, 1, 3, 100};
//        int[] nums = {2, 1, 1, 2};
//        int[] nums = {2, 7, 9, 3, 1};
//        int[] nums = {1, 2, 3, 1};
        int result = rob(nums);
        System.out.println(result);
    }

    private static int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] maxAmount = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                maxAmount[0] = nums[0];
                continue;
            }

            if (i == 1) {
                maxAmount[i] = Math.max(nums[1], nums[0]);
                continue;
            }

            maxAmount[i] = Math.max(maxAmount[i - 2] + nums[i], maxAmount[i - 1]);
        }

        return maxAmount[maxAmount.length - 1];
    }
}
