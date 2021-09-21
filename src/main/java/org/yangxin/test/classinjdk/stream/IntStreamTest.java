package org.yangxin.test.classinjdk.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author yangxin
 * 2020/02/15 21:20
 */
@SuppressWarnings({"AlibabaUndefineMagicConstant", "AlibabaRemoveCommentedCode", "CommentedOutCode"})
public class IntStreamTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test2() {
//        List<String> list = Arrays.asList("1", "2", "3", "4", "5");
//        list.stream().flatMapToInt(num -> IntStream.of(Integer.parseInt(num)))
//                .forEach(System.out::println);

        int[][] array = {{1, 2}, {3, 4}, {5, 6}};
        Arrays.stream(array).flatMapToInt(Arrays::stream).forEach(System.out::println);
    }

    private static void test1() {
        IntStream.range(1, 10).forEach(System.out::println);

        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
