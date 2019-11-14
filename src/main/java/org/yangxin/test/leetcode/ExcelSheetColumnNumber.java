package org.yangxin.test.leetcode;

/**
 * 返回对应列标题的号码
 *
 * @author yangxin
 * 2019/11/14 09:42
 */
public class ExcelSheetColumnNumber {
    public static void main(String[] args) {
        String A = "A";
        String B = "B";
        String C = "C";
        String Z = "Z";
        String Y = "Y";
        String AB = "AB";
        String AA = "AA";
        String ZY = "ZY";
        int result = titleToNumber(ZY);
        System.out.println(result);
    }

    private static int titleToNumber(String s) {
        String string = new StringBuilder(s).reverse().toString();
        char[] charArray = string.toCharArray();
        int sum = 0;

        for (int i = 0; i < charArray.length; i++) {
            sum += (charArray[i] - 'A' + 1) * Math.pow(26, i);
        }

        return sum;
    }
}
