package org.yangxin.test.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * @author yangxin
 * 2020/06/02 14:19
 */
public class ReverseVowelsOfAString {

    private static String reverseVowels(String s) {
        Set<Character> vowelSet = new HashSet<>();
        vowelSet.add('a');
        vowelSet.add('e');
        vowelSet.add('i');
        vowelSet.add('o');
        vowelSet.add('u');
        vowelSet.add('A');
        vowelSet.add('E');
        vowelSet.add('I');
        vowelSet.add('O');
        vowelSet.add('U');

        // i从数组起始位置找元音，j从数组结束位置找元音
        char[] cArray = s.toCharArray();
        int i = 0, j = cArray.length - 1;
        while (i < j) {
            while (i < j && !vowelSet.contains(cArray[i])) {
                i++;
            }
            while (i < j && !vowelSet.contains(cArray[j])) {
                j--;
            }

            if (i > j) {
                return new String(cArray);
            }

            char temp;
            temp = cArray[i];
            cArray[i] = cArray[j];
            cArray[j] = temp;

            i++;
            j--;
        }

        return new String(cArray);
    }

    public static void main(String[] args) {
        System.out.println(reverseVowels("hello"));
        System.out.println(reverseVowels("leetcode"));
        System.out.println(reverseVowels("aA"));
        System.out.println(reverseVowels(".,"));
    }
}
