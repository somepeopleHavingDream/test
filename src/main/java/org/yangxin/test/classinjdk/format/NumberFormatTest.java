package org.yangxin.test.classinjdk.format;

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
        // 默认
        double d = 12345.676688000;
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        System.out.println(numberInstance.format(d));

        // 按系统预设的货币格式输出
        numberInstance = NumberFormat.getCurrencyInstance();
        System.out.println(numberInstance.format(d));

        // 按指定的货币格式输出
        numberInstance = NumberFormat.getCurrencyInstance(Locale.CHINA);
        System.out.println(numberInstance.format(d));

        // 按系统预设百分比格式化
        numberInstance = NumberFormat.getPercentInstance();
        System.out.println(numberInstance.format(d));

        // 按指定百分比格式格式化
        numberInstance = NumberFormat.getPercentInstance(Locale.CHINA);
        System.out.println(numberInstance.format(d));
    }
}
