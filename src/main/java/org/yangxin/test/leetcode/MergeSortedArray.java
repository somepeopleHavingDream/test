package org.yangxin.test.leetcode;

/**
 * 合并有序数组
 *
 * @author yangxin
 * 2019/10/30 08:44
 */
public class MergeSortedArray {
    public static void main(String[] args) {
//        int[] nums1 = {3, 4, 5, 0, 0, 0};
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        int m = 3;
        int n = 3;
        merge(nums1, m, nums2, n);
        for (int num : nums1) {
            System.out.println(num);
        }
    }

    private static void merge(int[] nums1, int m, int[] nums2, int n) {
        int length = m;
        int j = 0;
        int i = 0;
        for (; i < n; i++) {
            // 在nums1中找到第一个比nums2当前元素大的元素位置
            while (nums2[i] >= nums1[j] && j < length) {
                j++;
            }

            System.arraycopy(nums1, j, nums1, j + 1, length++ - j);
            nums1[j++] = nums2[i];
        }
    }
}
