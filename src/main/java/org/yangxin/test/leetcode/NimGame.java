package org.yangxin.test.leetcode;

/**
 * @author yangxin
 * 2020/05/29 14:34
 */
public class NimGame {

    public static void main(String[] args) {
//        int n = 8;
//        int n = 6;
        int n = 4;
        System.out.println(canWinNim(n));
    }

    private static boolean canWinNim(int n) {
        // 我先手，石块的数量小于等于3，那么我直接赢了
        if (n <= 3) {
            return true;
        }

        // 对于当前拿石块的人来说，如果当前当前石块的数量是4的倍数，那么他总是输的，如果对方够聪明的话
        return n % 4 != 0;
    }
}
