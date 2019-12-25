package org.yangxin.test.leetcode;

/**
 * 统计素数
 *
 * @author yangxin
 * 2019/11/29 15:46
 */
public class CountPrimes {
    public static void main(String[] args) {
//        int result = countPrimes(3);
        int result = countPrimes(10);
        System.out.println(result);
    }

    public static int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            boolean flag = false;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    break;
                }

                flag = true;
            }

            if (flag) {
                count++;
            }
        }
        return count;
    }
}
