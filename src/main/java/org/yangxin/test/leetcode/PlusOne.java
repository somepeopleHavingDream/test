package org.yangxin.test.leetcode;

/**
 * 加一
 *
 * @author yangxin
 * 2019/10/25 09:39
 */
public class PlusOne {
    public static void main(String[] args) {
        int[] digitArr = new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
//        int[] digitArr = new int[]{4, 3, 2, 1};
//        int[] digitArr = new int[]{9};
//        int[] digitArr = new int[]{1, 2, 3};
        int[] result = plusOne(digitArr);
        for (int num : result) {
            System.out.print(num);
        }
    }

    private static int[] plusOne(int[] digits) {
        boolean flag = false;
        for (int i = digits.length - 1; i >= 0; i--) {
            // 当前数字不为9
            if (digits[i] != 9) {
                digits[i] += 1;
                return digits;
            }

            // 当前数字为9的情况
            digits[i] = 0;
            flag = true;
        }

        if (flag) {
            int[] newDigitArr = new int[digits.length + 1];
            newDigitArr[0] = 1;
            System.arraycopy(digits, 0, newDigitArr, 1, digits.length);
            return newDigitArr;
        }
        return digits;
    }
}
