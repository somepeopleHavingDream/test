package org.yangxin.test.leetcode;

/**
 * 有效的完全平方数
 *
 * @author yangxin
 * 2020/07/23 10:54
 */
public class ValidPerfectSquare {

    /**
     * 利用1+3+5+7+9+...+(2n-1)=n^2，即完全平方数肯定是前n个连续奇数的和
     */
    public static boolean isPerfectSquare(int num) {
        int sum = 1;
        while (num > 0) {
            num -= sum;
            sum += 2;
        }

        return num == 0;
    }

    public static void main(String[] args) {
        System.out.println(isPerfectSquare(16));
        System.out.println(isPerfectSquare(14));
        System.out.println(isPerfectSquare(1));
        System.out.println(isPerfectSquare(2147483647));
    }
}
