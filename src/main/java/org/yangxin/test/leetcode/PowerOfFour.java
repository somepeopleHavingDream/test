package org.yangxin.test.leetcode;

/**
 * @author yangxin
 * 2020/06/02 14:05
 */
public class PowerOfFour {

    private static boolean isPowerOfFour(int num) {
        return num == 1 || num == 4 || num ==16 || num == 64 || num == 256 || num == 1024 || num == 4096 || num == 16384 || num == 65536 || num == 262144 || num == 1048576 || num == 4194304 || num == 16777216 || num == 67108864 || num == 268435456 || num == 1073741824;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfFour(16));
        System.out.println(isPowerOfFour(5));
    }
}
