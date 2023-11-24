package org.yangxin.test.jdk8.format;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * 小数格式化
 *
 * @author yangxin
 * 2020/03/05 17:49
 */
@SuppressWarnings({"CommentedOutCode", "unused"})
public class DecimalFormatTest {

    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }

    private static void test5() {
        DecimalFormat df = new DecimalFormat("######0.0");
        double sum = 3038042.0;

        System.out.println(sum / 1000.0);
        System.out.println(df.format(sum / 1000.0));
    }

    /**
     * 货币格式
     */
    private static void test4() {
        // 创建一个中国地区的货币格式
        NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        DecimalFormat decimalFormat = (DecimalFormat) format;

        System.out.println(decimalFormat.format(0.912345));

        // 乘法，数乘以多少，这个方法是百分比的时候设置成100，千分比的时候是1000
        decimalFormat.setMultiplier(100);
        System.out.println(format.format(0.912345));
    }

    /**
     * 获取百分比格式
     */
    private static void test3() {
        // 创建一个中国地区的百分比格式
        NumberFormat format = NumberFormat.getPercentInstance(Locale.CHINA);
        DecimalFormat decimalFormat = (DecimalFormat) format;

        // 设置小数部分，最小位数为2
        decimalFormat.setMinimumFractionDigits(2);
        System.out.println(decimalFormat.format(0.912345));
    }

    /**
     * 通用格式
     */
    private static void test2() {
        // 创建一个默认的通用格式
        NumberFormat format = NumberFormat.getInstance();
        // 强制转换成DecimalFormat
        DecimalFormat decimalFormat = (DecimalFormat) format;

        // 保留小数点后面三位，不足的补零，前面整数部分，每隔四位，用“，”符合隔开
        decimalFormat.applyPattern("#,####.000");
        // 设置舍入模式，为DOWN，否则默认的是HALF_EVEN
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
        // 设置要格式化的数，是正数的时候，前面加前缀
        decimalFormat.setPositivePrefix("Prefix: ");
        System.out.println("正数前缀：" + decimalFormat.format(123456.7891));

        // 设置要格式化的数是正数的时候，后面加后缀
        decimalFormat.setPositiveSuffix(" :Suffix");
        System.out.println("正数后缀：" + decimalFormat.format(123456.7891));

        // 设置整数部分的最大位数
        decimalFormat.setMaximumIntegerDigits(3);
        System.out.println("整数最大位数：" + decimalFormat.format(123456.7891));

        // 设置整数部分的最小位数
        decimalFormat.setMinimumIntegerDigits(10);
        System.out.println("整数最小位数：" + decimalFormat.format(123456.7891));

        // 设置小数部分的最大位数
        decimalFormat.setMaximumFractionDigits(2);
        System.out.println("小数部分最大位数：" + decimalFormat.format(123.4));

        // 设置小数部分的最小位数
        decimalFormat.setMinimumFractionDigits(6);
        System.out.println("小数部分最小位数：" + decimalFormat.format(123.4));
    }

    /**
     * 通常，不要直接调用DecimalFormat构造函数，因为NumberFormat工厂方法可能返回DecimalFormat之外的子类。
     */
    private static void test1() {
        double d1 = 123456.36987, d2 = 12.3698;
        DecimalFormat format = new DecimalFormat("0000.000");
        System.out.println(format.format(d1) + " " + format.format(d2));

        format = new DecimalFormat("#");
        System.out.println(format.format(d1));

        format = new DecimalFormat(".####");
        System.out.println(format.format(d1));

        format = new DecimalFormat("0000,0000.0000");
        System.out.println(format.format(d1));
    }
}
