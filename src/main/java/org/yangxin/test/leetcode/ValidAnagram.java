package org.yangxin.test.leetcode;

import java.util.Arrays;
import java.util.Objects;

/**
 * 判断两个字符串是否是相同单词异序词
 *
 * @author yangxin
 * 2020/01/15 17:17
 */
public class ValidAnagram {
    public static boolean isAnagram(String s, String t) {
        if (Objects.equals(s, t)) {
            return true;
        }

        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        char[] sCharArray = s.toCharArray();
        char[] tCharArray = t.toCharArray();
        Arrays.sort(sCharArray);
        Arrays.sort(tCharArray);

        for (int i = 0; i < sCharArray.length; i++) {
            if (sCharArray[i] != tCharArray[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));
        System.out.println(isAnagram("rat", "car"));
        System.out.println(isAnagram("a", "ab"));
    }
}