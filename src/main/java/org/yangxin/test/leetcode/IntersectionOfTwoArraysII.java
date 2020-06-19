package org.yangxin.test.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class IntersectionOfTwoArraysII {

    private static int[] intersect(int[] nums1, int[] nums2) {
        // 先将两个数组升序
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));

        List<Integer> list = new LinkedList<>();
        int i = 0, j = 0;
        while (i < nums1.length && j < nums2.length) {
            while (i < nums1.length && nums1[i] < nums2[j]) {i++;}
            while (j < nums2.length && nums2[j] < nums1[i]) {j++;}

            if (j < nums2.length && nums1[i] == nums2[j]) {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }

        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] nums1 = {7, 2, 2, 4, 7, 0, 3, 4, 5};
//        int[] nums1 = {4, 9, 5};
//        int[] nums1 = {1, 2};
//        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {3, 9, 8, 6, 1, 9};
//        int[] nums2 = {9, 4, 9, 8, 4};
//        int[] nums2 = {2, 2};
//        int[] nums2 = {1, 1};
//        int[] nums2 = {2};
        System.out.println(Arrays.toString(intersect(nums1, nums2)));
    }
}
