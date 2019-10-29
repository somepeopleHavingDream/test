package org.yangxin.test.leetcode;

/**
 * 爬楼梯
 *
 * @author yangxin
 * 2019/10/29 08:36
 */
public class ClimbStairs {
    public static void main(String[] args) {
        int n = 1;
//        int n = 4;
//        int n = 3;
//        int n = 2;
        int result = climbStairs(n);
        System.out.println(result);
    }

    private static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }

        // resultArr[i]代表到第i（i>=0）层时，有几种爬法
        int[] resultArr = new int[n + 1];
        resultArr[1] = 1;
        resultArr[2] = 2;

        for (int i = 3; i <= n; i++) {
            resultArr[i] = resultArr[i - 1] + resultArr[i - 2];
        }
        return resultArr[n];
    }
}
