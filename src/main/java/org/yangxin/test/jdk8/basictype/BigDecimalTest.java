package org.yangxin.test.jdk8.basictype;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 大数用例
 *
 * @author yangxin
 * 2020/03/05 17:13
 */
@SuppressWarnings({"UnpredictableBigDecimalConstructorCall", "AlibabaBigDecimalAvoidDoubleConstructor", "CommentedOutCode", "unused"})
public class BigDecimalTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
        test4();
    }

    private static void test4() {
        // round_down=1，去掉多余的位数，不管后面数字的大小
        BigDecimal decimal = new BigDecimal("2.222222").setScale(2, RoundingMode.DOWN);
        System.out.println(decimal);
    }

    private static void test3() {
        System.out.println(BigDecimal.ZERO.compareTo(null));
    }

    private static void test2() {
        BigDecimal a = new BigDecimal(200);
        BigDecimal b = new BigDecimal(0);
        System.out.println(a.divide(b, 2, RoundingMode.HALF_EVEN));
    }

    private static void test1() {
        System.out.println(0.2 + 0.1);
        System.out.println(0.3 - 0.1);
        System.out.println(0.2 * 0.1);
        System.out.println(0.3 / 0.1);

        BigDecimal intStr = new BigDecimal("22");
        BigDecimal doubleStr = new BigDecimal(1.111111111);

        System.out.println(intStr);
        System.out.println(doubleStr);
    }
}
