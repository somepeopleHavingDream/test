package org.yangxin.test.leetcode;

/**
 * 快乐数
 *
 * @author yangxin
 * 2019/11/29 14:50
 */
public class HappyNumber {
    public static void main(String[] args) {
//        Boolean happy = isHappy(2);
//        Boolean happy = isHappy(3);
        Boolean happy = isHappy(19);
        System.out.println(happy);
    }

    public static Boolean isHappy(int n) {
        int sum = 0;
        while (n != 0) {
            sum += Math.pow(n % 10, 2);
            n /= 10;

            if (n != 0) {
                continue;
            }

            if (sum == 1) {
                return true;
            }

            n = sum;
            sum = 0;

            if (n == 4) {
                return false;
            }
        }

        return false;
    }
}
