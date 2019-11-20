package org.yangxin.test.leetcode;

/**
 * 旋转数组
 *
 * @author yangxin
 * 2019/11/19 09:55
 */
public class RotateArray {
    public static void main(String[] args) {
        int[] nums = {-1, -100, 3, 99};
//        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 2;
        rotate(nums, k);

        for (int num : nums) {
            System.out.println(num);
        }
    }

    private static void rotate(int[] nums, int k) {
        for (int i = 0; i < k; i++) {
            int length = nums.length;
            int tmp = nums[length - 1];
            System.arraycopy(nums, 0, nums, 1, length - 1);
            nums[0] = tmp;
        }
    }
}
