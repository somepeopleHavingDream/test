package org.yangxin.test.jdk8.basictype;

/**
 * @author yangxin
 * 2021/8/5 14:17
 */
@SuppressWarnings({"WrapperTypeMayBePrimitive", "ConstantConditions", "CommentedOutCode", "unused"})
public class LongTest {

    public static void main(String[] args) {
//        test1();
//        test2();
        test3();
    }

    private static void test3() {
        System.out.println(((int) -2814797011746816L));
    }

    private static void test2() {
        System.out.println(10000002515L % 30);

        Long num = 0L;
        System.out.println(Long.valueOf(num % 30).intValue());
    }

    private static void test1() {
        System.out.println(0xFFL);
        System.out.println(0xFF);
    }
}
