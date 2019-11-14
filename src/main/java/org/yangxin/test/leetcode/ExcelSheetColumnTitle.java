package org.yangxin.test.leetcode;

/**
 * 表头列名称
 *
 * @author yangxin
 * 2019/11/14 08:55
 */
public class ExcelSheetColumnTitle {
    public static void main(String[] args) {
        String result = convertToTitle(701);
//        String result = convertToTitle(28);
//        String result = convertToTitle(27);
//        String result = convertToTitle(26);
//        String result = convertToTitle(1);
        System.out.println(result);
    }

    private static String convertToTitle(int n) {
        StringBuilder stringBuilder = new StringBuilder();

        while (n > 0) {
            stringBuilder.append((char) (--n % 26 + 'A'));
            n /= 26;
        }
        return stringBuilder.reverse().toString();
    }
}
