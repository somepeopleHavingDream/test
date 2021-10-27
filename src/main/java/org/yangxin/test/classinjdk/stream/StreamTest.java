package org.yangxin.test.classinjdk.stream;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        test4();
    }

    private static void test4() {
        List<String> list = Arrays.asList("456", "123", "789");
        list.stream().sorted(((o1, o2) -> {
            int a1 = Integer.parseInt(String.valueOf(o1.charAt(0)));
            int a2 = Integer.parseInt(String.valueOf(o2.charAt(0)));

            return Integer.compare(a1, a2);
        })).forEach(System.out::println);
    }

    private static void test3() {
        int[][] array = {{1, 2}, {3, 4}, {5, 6}};
        Arrays.stream(array).flatMapToInt(Arrays::stream).forEach(System.out::println);
    }

    private static void test2() {
        IntStream.range(1, 10).forEach(System.out::println);

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += i;
        }
        System.out.println(sum);
    }

    private static void test1() {
        List<String> list = Arrays.asList("456", "123", "789");
        list.stream()
                .collect(Collectors.toCollection(LinkedList::new))
                .forEach(System.out::println);
    }
}
