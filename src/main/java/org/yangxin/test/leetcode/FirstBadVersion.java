package org.yangxin.test.leetcode;

/**
 * @author yangxin
 * 2020/05/28 16:14
 */
public class FirstBadVersion {

    public static void main(String[] args) {
        System.out.println(firstBadVersion(5));
        System.out.println(firstBadVersion(1));
    }

    private static int firstBadVersion(int n) {
        int low = 0, high = n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (isBadVersion(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private static boolean isBadVersion(int mid) {
        return mid >= 4;
    }
}
