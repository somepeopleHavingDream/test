package org.yangxin.test.jdk8.basictype;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;

/**
 * 大数用例
 *
 * @author yangxin
 * 2020/03/05 17:13
 */
@SuppressWarnings({"UnpredictableBigDecimalConstructorCall", "AlibabaBigDecimalAvoidDoubleConstructor", "CommentedOutCode"})
public class BigDecimalTest {

    public static void main(String[] args) {
//        construct();
//        format();
//        equalsAndCompareTo();

//        test1();
//        test2();
        test3();
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

    private static void construct() {
        BigDecimal bigDecimal = new BigDecimal("0.1");
        System.out.println(bigDecimal);
    }

    private static void format() {
        // 建立货币格式化引用
        NumberFormat currencyInstance = NumberFormat.getCurrencyInstance();
        // 建立百分比格式化引用
        NumberFormat percentInstance = NumberFormat.getPercentInstance();

        // 贷款金额
        BigDecimal loanAmount = new BigDecimal("15000.48");
        // 利率
        BigDecimal interestRate = new BigDecimal("0.008");
        // 利息
        BigDecimal interest = loanAmount.multiply(interestRate);

        System.out.println(currencyInstance.format(loanAmount));
        System.out.println(percentInstance.format(interestRate));
        System.out.println(currencyInstance.format(interest));
    }

    @SuppressWarnings("UnpredictableBigDecimalConstructorCall")
    private static void equalsAndCompareTo() {
        BigDecimal bigDecimal = new BigDecimal(1);
        BigDecimal bigDecimal1 = new BigDecimal(1);
        System.out.println(bigDecimal.equals(bigDecimal1));

        BigDecimal bigDecimal2 = new BigDecimal(1);
        BigDecimal bigDecimal3 = new BigDecimal(1.0);
        System.out.println(bigDecimal2.equals(bigDecimal3));

        BigDecimal bigDecimal4 = new BigDecimal("1");
        BigDecimal bigDecimal5 = new BigDecimal("1.0");
        System.out.println(bigDecimal4.equals(bigDecimal5));
    }
}
