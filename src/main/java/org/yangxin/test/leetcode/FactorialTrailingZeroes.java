package org.yangxin.test.leetcode;

/**
 * 阶乘后有几个尾0
 *
 * @author yangxin
 * 2019/11/15 09:45
 */
public class FactorialTrailingZeroes {
    public static void main(String[] args) {
//        int result = trailingZeroes(4);
//        int result = trailingZeroes(9);
        int result = trailingZeroes(30);
//        int result = trailingZeroes(5);
//        int result = trailingZeroes(3);
        System.out.println(result);
    }

    private static int trailingZeroes(int n) {
        int count = 0;
        while (n >= 5) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }
}