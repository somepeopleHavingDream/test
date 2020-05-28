package org.yangxin.test.leetcode;

/**
 * @author yangxin
 * 2020/05/28 14:54
 */
public class UglyNumber {

    public static void main(String[] args) {
        System.out.println(isUgly(6));
        System.out.println(isUgly(8));
        System.out.println(isUgly(14));
    }

    private static boolean isUgly(int num) {
        if (num == 0) {
            return false;
        }

        while (num % 5 == 0) {
            num /= 5;
        }
        while (num % 3 == 0) {
            num /= 3;
        }
        while (num % 2 == 0) {
            num /= 2;
        }

        return num == 1;
    }
}
