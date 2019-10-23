package org.yangxin.test.leetcode;

/**
 * 找到指定字符串在字符串中第一次出现的下标
 *
 * @author yangxin
 * 2019/10/23 09:30
 */
public class StrStr {
    public static void main(String[] args) {
        String haystack = "aaaaa";
//        String haystack = "hello";
        String needle = "";
//        String needle = "bba";
//        String needle = "ll";
        int result = strStr(haystack, needle);
        System.out.println(result);
    }

    private static int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
