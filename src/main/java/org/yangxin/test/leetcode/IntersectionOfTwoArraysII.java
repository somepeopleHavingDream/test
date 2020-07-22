package org.yangxin.test.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntersectionOfTwoArraysII {

    private static int[] intersect(int[] nums1, int[] nums2) {
        // 先将数组升序，如：num1: 1, 2, 3 num2: 1, 1
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        List<Integer> resultList = new ArrayList<>();
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            // 如果两个数组当前的位置的元素值相同，则进入线性表
            if (nums1[i] == nums2[j]) {
                resultList.add(nums1[i]);
                i++;
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }

        int[] results = new int[resultList.size()];
        for (int k = 0; k < results.length; k++) {
            results[k] = resultList.get(k);
        }
        return results;
    }

    public static void main(String[] args) {
//        int[] nums1 = {1, 2, 2, 1};
        int[] nums1 = {3, 1, 2};
//        int[] nums2 = {2, 2};
//        int[] nums1 = {4, 9, 5};
//        int[] nums2 = {9, 4, 9, 8, 4};
        int[] nums2 = {1,  1};
//        int[] nums2 = {2};
        System.out.println(Arrays.toString(intersect(nums1, nums2)));
    }
}
