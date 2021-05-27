package org.yangxin.test.datetime;

import org.yangxin.test.list.Simple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时期字符串案例
 *
 * @author yangxin
 * 2021/5/27 11:35
 */
public class DateStringTest {

    public static void main(String[] args) throws ParseException {
        test1();
    }

    /**
     * 字符串转Date
     *
     * @throws ParseException 解析异常
     */
    private static void test1() throws ParseException {
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyyMMddHHmm");
        String sendTime = "202105261542";
        Date date = simpleDateFormat2.parse(sendTime);
        System.out.println(date);

        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("YYYY-MM-DD HH24:MI");
        System.out.println(simpleDateFormat1.format(date));
    }
}
