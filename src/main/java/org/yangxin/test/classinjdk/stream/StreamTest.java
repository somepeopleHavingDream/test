package org.yangxin.test.classinjdk.stream;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author yangxin
 * 2021/10/27 9:55
 */
@SuppressWarnings({"SimplifyStreamApiCallChains", "CommentedOutCode", "AlibabaUndefineMagicConstant"})
public class StreamTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
        test7();
    }

    /**
     * 总共取7个数，先取奇数，再取偶数
     */
    private static void test7() {
        List<Integer> list = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).collect(Collectors.toList());
        
        // 先收集奇数
        List<Integer> resultList = list.stream()
                .filter(i -> (i & 1) == 1)
                .limit(7)
                .collect(Collectors.toList());
        // 如果可以的话，收集偶数
        int remain = resultList.size();
        if (remain < 7) {
            List<Integer> evenList = list.stream()
                    .filter(i -> (i & 1) == 0)
                    .limit(7 - remain)
                    .collect(Collectors.toList());
            resultList.addAll(evenList);
        }

        System.out.println(resultList);
    }

    /**
     * 基于stream的分页
     */
    private static void test6() {
        IntStream.range(1, 20)
                .skip(5 * 2)
                .limit(5)
                .forEach(System.out::println);
    }

    /**
     * java.util.stream.Stream#peek(java.util.function.Consumer)
     */
    private static void test5() {
        Stream.of("one", "two", "three", "four")
                .peek(System.out::println)
                .forEach(System.out::println);
    }

    /**
     * java.util.stream.Stream#sorted(java.util.Comparator)
     */
    private static void test4() {
        List<String> list = Arrays.asList("456", "123", "789");
        list.stream().sorted(((o1, o2) -> {
            int a1 = Integer.parseInt(String.valueOf(o1.charAt(0)));
            int a2 = Integer.parseInt(String.valueOf(o2.charAt(0)));

            return Integer.compare(a1, a2);
        })).forEach(System.out::println);
    }

    /**
     * java.util.stream.Stream#flatMapToInt(java.util.function.Function)
     */
    private static void test3() {
        int[][] array = {{1, 2}, {3, 4}, {5, 6}};
        Arrays.stream(array).flatMapToInt(Arrays::stream).forEach(System.out::println);
    }

    /**
     * java.util.stream.IntStream#range(int, int)
     */
    private static void test2() {
        IntStream.range(1, 10).forEach(System.out::println);

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += i;
        }
        System.out.println(sum);
    }

    /**
     * java.util.stream.Collectors#toCollection(java.util.function.Supplier)
     */
    private static void test1() {
        List<String> list = Arrays.asList("456", "123", "789");
        list.stream()
                .collect(Collectors.toCollection(LinkedList::new))
                .forEach(System.out::println);
    }
}
