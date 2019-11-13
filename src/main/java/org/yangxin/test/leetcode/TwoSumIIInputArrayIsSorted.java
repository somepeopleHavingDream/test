package org.yangxin.test.leetcode;

import java.util.Arrays;

/**
 * 找到排序数组中两个数的下标，这两个数加起来等于给定值
 *
 * @author yangxin
 * 2019/11/13 09:19
 */
public class TwoSumIIInputArrayIsSorted {
    public static void main(String[] args) {
        int[] numbers = {-1, 0};
//        int[] numbers = {2, 7, 11, 15};
        int target = -1;
        int[] result = twoSum(numbers, target);
        System.out.println(Arrays.toString(result));
    }

    private static int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length - 1; i++) {
            // 如果第一个值就大于等于给定目标值，则直接返回null
            if (numbers[i] > target) {
                return null;
            }

            // 找到等于第二个数的元素
            int otherNum = target - numbers[i];
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[j] > otherNum) {
                    continue;
                }

                if (numbers[j] == otherNum) {
                    int[] result = new int[2];
                    result[0] = i + 1;
                    result[1] = j + 1;

                    return result;
                }
            }
        }

        return null;
    }
}
