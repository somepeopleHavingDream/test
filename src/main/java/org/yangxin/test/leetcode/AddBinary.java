package org.yangxin.test.leetcode;

/**
 * 二进制相加
 *
 * @author yangxin
 * 2019/10/28 10:03
 */
public class AddBinary {
    public static void main(String[] args) {
        String a = "1010";
//        String a = "11";
        String b = "1011";
//        String b = "1";
        String result = addBinary(a, b);
        System.out.println(result);
    }

    private static String addBinary(String a, String b) {
        String minStr = a.length() < b.length() ? a : b;
        String maxStr = a.length() >= b.length() ? a : b;

        // 将长度较小的字符串，向前补0至较长串同长度
        StringBuilder builder = new StringBuilder(minStr);
        for (int i = 0; i < maxStr.length() - minStr.length(); i++) {
            builder.insert(0, '0');
        }
        minStr = builder.toString();

        char[] maxCharArr = maxStr.toCharArray();
        char[] minCharArr = minStr.toCharArray();
        boolean flag = false;
        for (int i = maxCharArr.length - 1; i >= 0; i--) {
            char num1 = maxCharArr[i];
            char num2 = minCharArr[i];

            // 存在进位
            if (flag) {
                flag = false;
                if (num1 == '1') {
                    num1 = '0';
                    flag = true;
                } else {
                    num1 = '1';
                }
            }

            // 同为1
            if (num1 == '1' && num2 == '1') {
                maxCharArr[i] = '0';
                flag = true;
                continue;
            }

            // 互不同
            if (num1 != num2) {
                maxCharArr[i] = '1';
                continue;
            }

            // 互为0
            if (num1 == '0' && num2 == '0') {
                maxCharArr[i] = '0';
            }
        }

        if (flag) {
            char[] result = new char[maxCharArr.length + 1];
            result[0] = '1';
            System.arraycopy(maxCharArr, 0, result, 1, maxCharArr.length);
            return new String(result);
        }

        return new String(maxCharArr);
    }
}
