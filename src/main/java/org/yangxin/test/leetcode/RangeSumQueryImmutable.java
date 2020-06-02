package org.yangxin.test.leetcode;

/**
 * @author yangxin
 * 2020/06/02 13:49
 */
public class RangeSumQueryImmutable {

    private final int[] nums;

    public RangeSumQueryImmutable(int[] nums) {
        this.nums = nums;
    }

    public int sumRange(int i, int j) {
        int sum = 0;
        for (int k = i; k <= j; k++) {
            sum += nums[k];
        }
        return sum;
    }

    public static void main(String[] args) {
        RangeSumQueryImmutable rangeSumQueryImmutable = new RangeSumQueryImmutable(new int[]{-2, 0, 3, -5, 2, -1});
        System.out.println(rangeSumQueryImmutable.sumRange(0, 2));
        System.out.println(rangeSumQueryImmutable.sumRange(2, 5));
        System.out.println(rangeSumQueryImmutable.sumRange(0, 5));
    }
}
