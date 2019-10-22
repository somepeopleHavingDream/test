package org.yangxin.test.leetcode;

import java.util.*;

/**
 * 合法字符串
 *
 * @author yangxin
 * 2019/10/22 10:14
 */
public class ValidParentheses {
    public static void main(String[] args) {
//        boolean result = isValid("()");
//        boolean result = isValid("[");
//        boolean result = isValid("{[]}");
//        boolean result = isValid("(]");
        boolean result = isValid("()[]{}");
        System.out.println(result);
    }

    private static boolean isValid(String s) {
        // 将字符串转换成字符数组
        char[] charArr = s.toCharArray();

        // 左括号集合
        Set<Character> openBracketSet = new HashSet<>();
        openBracketSet.add('(');
        openBracketSet.add('{');
        openBracketSet.add('[');

        // 左括号与右括号的映射关系
        Map<Character, Character> bracketMap = new HashMap<>();
        bracketMap.put('(', ')');
        bracketMap.put('{', '}');
        bracketMap.put('[', ']');

        LinkedList<Character> charStack = new LinkedList<>();
        for (char c : charArr) {
            // 当前字符是左括号，入栈
            if (openBracketSet.contains(c)) {
                charStack.push(c);
                continue;
            }

            // 当前字符是右括号，出栈
            Character peek = charStack.peek();
            if (!Objects.equals(bracketMap.get(peek), c)) {
                return false;
            }

            // 栈空，则合法
//            if (charStack.isEmpty()) {
//                return true;
//            }

            charStack.pop();
        }

        return charStack.isEmpty();
    }
}
