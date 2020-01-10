package org.yangxin.test.leetcode;

/**
 * 判断一个数是否是2的幂
 *
 * @author yangxin
 * 2020/01/10 14:41
 */
public class PowerOfTwo {
    /**
     * 判断偶数的方法和判断幂的方式是不一样的
     */
    private static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & n - 1) == 0;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(1));
        System.out.println(isPowerOfTwo(16));
        System.out.println(isPowerOfTwo(218));
        System.out.println(isPowerOfTwo(0));
    }
}
