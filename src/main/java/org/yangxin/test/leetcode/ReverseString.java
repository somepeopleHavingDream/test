package org.yangxin.test.leetcode;

/**
 * @author yangxin
 * 2020/06/02 14:12
 */
public class ReverseString {

    private static void reverseString(char[] s) {
        for (int i = 0; i < s.length / 2; i++) {
            char temp;
            temp = s[i];
            s[i] = s[s.length - i - 1];
            s[s.length - i - 1] = temp;
        }
    }

    public static void main(String[] args) {
        char[] s = {'H', 'a', 'n', 'n', 'a', 'h'};
//        char[] s = {'h', 'e', 'l', 'l', 'o'};
        reverseString(s);
        for (char c : s) {
            System.out.print(c);
        }
    }
}
