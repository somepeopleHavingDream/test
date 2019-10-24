package org.yangxin.test.leetcode;

/**
 * 最后一个单词的长度
 *
 * @author yangxin
 * 2019/10/24 11:54
 */
public class LengthOfLastWord {
    public static void main(String[] args) {
//        String s = "";
//        String s = "a";
        String s = "Hello World";
        int result = lengthOfLastWord(s);
        System.out.println(result);
    }

    private static int lengthOfLastWord(String s) {
        int lastWordLength = 0;

        char[] charArr = s.toCharArray();
        int i = charArr.length - 1;

        // 从后往前找到第一个不是空格的字符或者字符串已被遍历完时退出循环
        while (i >= 0 && charArr[i] == ' ') {
            i--;
        }

        if (i == -1) {
            return 0;
        }

        while (i >= 0 && charArr[i--] != ' ') {
            lastWordLength++;
        }

        return lastWordLength;
    }
}
