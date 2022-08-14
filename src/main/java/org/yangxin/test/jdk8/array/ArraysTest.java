package org.yangxin.test.jdk8.array;

import java.util.Arrays;

/**
 * @author yangxin
 * 2021/10/13 9:28
 */
@SuppressWarnings({"MismatchedReadAndWriteOfArray", "CommentedOutCode"})
public class ArraysTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }

    private static void test4() {
        int[] numArray = {-2, -1, 0, 1, 2};
        Integer[] nums = Arrays.stream(numArray).boxed().toArray(Integer[]::new);
        Arrays.sort(nums, (o1, o2) -> Math.abs(o2) - Math.abs(o1));

        System.out.println(Arrays.toString(nums));
    }

    private static void test3() {
        int[] nums = new int[201];
        System.out.println(Arrays.toString(nums));

        boolean[] bool = new boolean[10];
        System.out.println(Arrays.toString(bool));
    }

    private static void test2() {
        int[] num = {1, 2, 3, 4, 5, 6};
        System.out.println(Arrays.toString(Arrays.copyOfRange(num, 0, 1)));
    }

    private static void test1() {
        String[] array = new String[10];
        Arrays.fill(array, "hello world");
        System.out.println(Arrays.toString(array));
    }
}
