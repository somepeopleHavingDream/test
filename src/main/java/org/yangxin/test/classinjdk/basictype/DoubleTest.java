package org.yangxin.test.classinjdk.basictype;

/**
 * 数学运算使用用例
 *
 * @author yangxin
 * 2020/05/08 14:59
 */
public class DoubleTest {

    public static void main(String[] args) {
//        test1();
    }

    private static void test1() {
        // 保留后3位小数
        double a = 0.26666666666666666;
        System.out.println((double) Math.round(a * 1000) / 1000);

        double d = 114.145;
        d = (double) Math.round(d * 100) / 100;
        System.out.println(d);

        double b = (double) 400 / 1165;
        b = (double) Math.round(b * 100) / 100;
        System.out.println(b);
    }
}
