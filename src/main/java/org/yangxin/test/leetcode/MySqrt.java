package org.yangxin.test.leetcode;

/**
 * 返回平方根
 *
 * @author yangxin
 * 2019/10/28 13:56
 */
public class MySqrt {
    public static void main(String[] args) {
        int x = 8;
//        int x = 4;
        int result = mySqrt(x);
        System.out.println(result);
    }

    private static int mySqrt(int x) {
        return (int) Math.sqrt(x);
    }
}
