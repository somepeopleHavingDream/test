package org.yangxin.test.classinjdk.format;

import java.text.MessageFormat;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @author yangxin
 * 2022/1/15 16:11
 */
public class MessageFormatTest {

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        // 信息格式化串
        String pattern1 = "{0}，您好！您于{1}在工商银行存入{2}元。";
        String pattern2 = "At {1,time,short} On {1,date,long}, {0} paid {2,number,currency}.";

        // 用于动态替换占位符的参数
        Object[] params = {"John", new GregorianCalendar().getTime(), 1.0E3};

        // 使用默认本地化对象格式化信息
        String msg1 = MessageFormat.format(pattern1, params);
        System.out.println(msg1);

        // 使用指定的本地化对象格式化信息
        MessageFormat messageFormat = new MessageFormat(pattern2, Locale.US);
        String msg2 = messageFormat.format(params);
        System.out.println(msg2);
    }
}
