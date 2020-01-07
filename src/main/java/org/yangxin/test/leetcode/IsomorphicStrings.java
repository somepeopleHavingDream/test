package org.yangxin.test.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 同构字符串
 *
 * @author yangxin
 * 2020/01/06 17:52
 */
public class IsomorphicStrings {
    private static boolean isIsomorphic(String s, String t) {
        // 如果长度不等，则绝对不是同构的
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Character> map = new HashMap<>();
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        for (int i = 0; i < sArr.length; i++) {
            // 如果没有存在匹配关系
            if (!map.containsKey((sArr[i])) && !map.containsValue(tArr[i])) {
                map.put(sArr[i], tArr[i]);
                continue;
            }

            // 如果存在匹配关系
            if (!Objects.equals(map.get(sArr[i]), tArr[i])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println(isIsomorphic("egg", "add"));
//        System.out.println(isIsomorphic("foo", "bar"));
//        System.out.println(isIsomorphic("paper", "title"));
//        System.out.println(isIsomorphic("ab", "aa"));
    }
}
