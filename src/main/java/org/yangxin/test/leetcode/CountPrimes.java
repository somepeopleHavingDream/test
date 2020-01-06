package org.yangxin.test.leetcode;

import java.util.Arrays;

/**
 * 统计素数
 *
 * @author yangxin
 * 2019/11/29 15:46
 */
public class CountPrimes {
    public static void main(String[] args) {
        int result = countPrimes(4);
//        int result = countPrimes(3);
//        int result = countPrimes(0);
//        int result = countPrimes(10);
        System.out.println(result);
    }

    public static int countPrimes(int n) {
        // isPrime[i]=true代表i是个素数
        boolean[] isPrime = new boolean[n];

        // 赋初值
        Arrays.fill(isPrime, true);

        // 考虑该数是否是个素数
        for (int i = 2; i < Math.sqrt(n); i++) {
            // 如果这个数是个素数，则所有它的倍数都不是素数
            if (isPrime[i]) {
                for (int j = i * 2; j < n; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        int count = 0;
        for (int i = 2; i < n; i++) {
            count += isPrime[i] ? 1 : 0;
        }

        return count;
    }
}
