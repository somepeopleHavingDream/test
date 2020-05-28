package org.yangxin.test.leetcode;

public class AddDigits {

    public static void main(String[] args) {
        System.out.println(addDigits(38));
        System.out.println(addDigits(0));
    }

    private static int addDigits(int num) {
        if (num < 9) {
            return num;
        }

        num = num % 9;
        if (num == 0) {
            return 9;
        }
        return num;
    }
}
