package org.yangxin.test.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yangxin
 * 2020/06/04 17:18
 */
public class IntersectionOfTwoArrays {

    private static int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = Arrays.stream(nums1).boxed().collect(Collectors.toSet());
        set.retainAll(Arrays.stream(nums2).boxed().collect(Collectors.toSet()));

        return set.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        int[] nums1 = {4, 9, 5};
//        int[] nums1 = {1, 2, 2, 1};
        int[] nums2 = {9, 4, 9, 8, 4};
//        int[] nums2 = {2, 2};
        System.out.println(Arrays.toString(intersection(nums1, nums2)));
    }
}
