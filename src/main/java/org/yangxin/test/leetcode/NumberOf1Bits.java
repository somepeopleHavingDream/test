package org.yangxin.test.leetcode;

/**
 * 获取二进制1的个数
 *
 * @author yangxin
 * 2019/11/22 09:56
 */
public class NumberOf1Bits {
    public static void main(String[] args) {

    }

    private static int hammingWeight(int n) {
        return Integer.bitCount(n);
    }
}
