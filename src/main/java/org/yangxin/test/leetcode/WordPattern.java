package org.yangxin.test.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yangxin
 * 2020/05/29 14:12
 */
public class WordPattern {

    public static void main(String[] args) {
//        String pattern = "aaaa";
        String pattern = "abba";
//        String str = "dog cat cat fish";
//        String str = "dog dog dog dog";
        String str = "dog cat cat dog";
        System.out.println(wordPattern(pattern, str));
    }

    private static boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        char[] patterns = pattern.toCharArray();

        if (words.length != pattern.length()) {
            return false;
        }

        Map<Character, String> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            // 如果没有对应的映射
            if (!map.containsKey(patterns[i]) && !map.containsValue(words[i])) {
                map.put(patterns[i], words[i]);
                continue;
            }

            String value = map.get(patterns[i]);
            if (!Objects.equals(value, words[i])) {
                return false;
            }
        }

        return true;
    }
}
