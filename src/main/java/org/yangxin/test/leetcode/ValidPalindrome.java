package org.yangxin.test.leetcode;

import java.util.Objects;

/**
 * 合法回文数
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        String s1 = "A man, a plan, a canal: Panama";
        String s2 = "race a car";
        boolean result = isPalindrome(s2);
        System.out.println(result);
    }

    private static boolean isPalindrome(String s) {
        s = s.replaceAll("\\W", "").toUpperCase();
        for (int i = 0; i < s.length(); i++) {
            if (!Objects.equals(s.charAt(i), s.charAt(s.length() - 1 -i))) {
                return false;
            }
        }

        return true;
    }
}
