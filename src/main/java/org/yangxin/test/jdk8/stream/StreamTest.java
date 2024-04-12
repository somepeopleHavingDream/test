package org.yangxin.test.jdk8.stream;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author yangxin
 * 2021/10/27 9:55
 */
@SuppressWarnings({"CommentedOutCode", "AlibabaUndefineMagicConstant", "MismatchedQueryAndUpdateOfCollection", "RedundantOperationOnEmptyContainer", "unused"})
public class StreamTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
//        test8();
//        test9();
        test10();
    }

    /**
     * java.util.stream.Stream#generate(java.util.function.Supplier)
     */
    private static void test10() {
        // 使用 generate 方法创建一个无限的自然数序列
        AtomicInteger count = new AtomicInteger();
        Stream<Integer> naturalNumbers = Stream.generate(count::getAndIncrement);

        // 打印前10个自然数
        naturalNumbers.limit(10).forEach(System.out::println);
    }

    /**
     * java.util.stream.Stream#toArray(java.util.function.IntFunction)
     */
    private static void test8() {
        Stream<String> stream = Stream.of("张三", "李四", "王五");
        String[] array = stream.toArray(String[]::new);
        System.out.println(Arrays.toString(array));
    }

    /**
     * java.util.stream.IntStream#skip(long)
     * java.util.stream.IntStream#limit(long)
     */
    private static void test6() {
        IntStream intStream = IntStream.range(1, 20);
        IntStream skip = intStream.skip(5 * 2);
        IntStream limit = skip.limit(5);
        limit.forEach(System.out::println);
    }

    /**
     * java.util.stream.Stream#peek(java.util.function.Consumer)
     */
    private static void test5() {
        Stream<String> stream = Stream.of("one", "two", "three", "four");
        Stream<String> peek = stream.peek(System.out::println);
        peek.forEach(System.out::println);
    }

    /**
     * java.util.stream.Stream#sorted(java.util.Comparator)
     */
    private static void test4() {
        List<String> list = Arrays.asList("456", "123", "789");
        Stream<String> stream = list.stream();
        Stream<String> sorted = stream.sorted(((o1, o2) -> {
            int a1 = Integer.parseInt(String.valueOf(o1.charAt(0)));
            int a2 = Integer.parseInt(String.valueOf(o2.charAt(0)));

            return Integer.compare(a1, a2);
        }));
        sorted.forEach(System.out::println);
    }

    /**
     * java.util.stream.Stream#flatMapToInt(java.util.function.Function)
     */
    private static void test3() {
        int[][] array = {{1, 2}, {3, 4}, {5, 6}};

        Stream<int[]> stream = Arrays.stream(array);
        IntStream intStream = stream.flatMapToInt(Arrays::stream);
        intStream.forEach(System.out::println);
    }

    /**
     * java.util.stream.IntStream#range(int, int)
     */
    private static void test2() {
        IntStream intStream = IntStream.range(1, 10);
        intStream.forEach(System.out::println);

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
        Stream<String> stream = list.stream();
        Collector<String, ?, LinkedList<String>> collector = Collectors.toCollection(LinkedList::new);
        LinkedList<String> linkedList = stream.collect(collector);
//        linkedList.forEach(System.out::println);
    }
}
