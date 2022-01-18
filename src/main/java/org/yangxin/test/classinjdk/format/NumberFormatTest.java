package org.yangxin.test.classinjdk.format;

import java.text.FieldPosition;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * 数值格式化
 *
 * @author yangxin
 * 2020/03/05 17:44
 */
public class NumberFormatTest {

    public static void main(String[] args) {
        test1();
//        test2();
    }

    private static void test2() {
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());

        // 整数部分不会每隔三个，就会有“，”
        format.setGroupingUsed(false);

        // 线程安全的字符串缓冲类
        StringBuffer buffer = new StringBuffer();

        FieldPosition position = new FieldPosition(NumberFormat.FRACTION_FIELD);
        buffer = format.format(1234.56789, buffer, position);
        System.out.println(buffer.toString());

        // 小数部分，所以从5开始
        System.out.println(position.getBeginIndex() + " " + position.getEndIndex());

        // 切割字符串
        System.out.println(buffer.toString().substring(position.getBeginIndex()));
    }

    private static void test1() {
        // 默认
        double d = 12345.676688000;
        NumberFormat format = NumberFormat.getNumberInstance();
        System.out.println(format.format(d));

        /*
            货币
         */

        // 按系统预设的货币格式输出
        format = NumberFormat.getCurrencyInstance();
        System.out.println(format.format(d));

        // 按指定的货币格式输出
        format = NumberFormat.getCurrencyInstance(Locale.CHINA);
        System.out.println(format.format(d));

        /*
            百分比
         */

        // 按系统预设百分比格式化
        format = NumberFormat.getPercentInstance();
        System.out.println(format.format(d));

        // 按指定百分比格式格式化
        format = NumberFormat.getPercentInstance(Locale.CHINA);
        System.out.println(format.format(d));
    }
}
