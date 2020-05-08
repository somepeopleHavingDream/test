package org.yangxin.test.math;

/**
 * 数学运算使用用例
 *
 * @author yangxin
 * 2020/05/08 14:59
 */
public class MathTest {

    public static void main(String[] args) {
        // 保留后3位小数
        double a = 0.26666666666666666;
        System.out.println((double) Math.round(a * 1000) / 1000);

        double d = 114.145;
        d = (double) Math.round(d * 100) / 100;
        System.out.println(d);
    }
}
