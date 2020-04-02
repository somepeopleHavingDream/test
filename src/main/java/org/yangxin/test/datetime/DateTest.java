package org.yangxin.test.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date
 *
 * @author yangxin
 * 2020/02/12 11:39
 */
public class DateTest {
    public static void main(String[] args) {
//        Date date = new Date(2014, 6, 12);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat.format(date));
    }
}
