package org.yangxin.test.leetcode;

/**
 * 返回数组中与给定值相同的第一个元素的下标或返回要插入位置的下标
 *
 * @author yangxin
 * 2019/10/23 09:47
 */
public class SearchInsert {
    public static void main(String[] args) {
        int[] nums = new int[]{1, 3, 5, 6};
        int target = 7;
//        int target = 2;
//        int target = 5;
        int result = searchInsert(nums, target);
        System.out.println(result);
    }

    private static int searchInsert(int[] nums, int target) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            if (nums[j] == target || target < nums[j]) {
                return j;
            }
        }
        return nums.length;
    }
}
