package org.yangxin.test.format;

import java.text.DecimalFormat;

/**
 * 小数格式化
 *
 * @author yangxin
 * 2020/03/05 17:49
 */
public class DecimalFormatTest {

    public static void main(String[] args) {
        double d1 = 123456.36987, d2 = 12.3698;
        DecimalFormat decimalFormat = new DecimalFormat("0000.000");
        System.out.println(decimalFormat.format(d1) + " " + decimalFormat.format(d2));

        decimalFormat = new DecimalFormat("#");
        System.out.println(decimalFormat.format(d1));

        decimalFormat = new DecimalFormat(".####");
        System.out.println(decimalFormat.format(d1));

        decimalFormat = new DecimalFormat("0000,0000.0000");
        System.out.println(decimalFormat.format(d1));
    }
}
