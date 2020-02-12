package org.yangxin.test.yearold;

import java.util.Calendar;
import java.util.Date;

/**
 * 周岁
 *
 * @author yangxin
 * 2020/02/12 11:05
 */
public class YearOldTest {
    public static void main(String[] args) {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2000, Calendar.OCTOBER, 1);
//        System.out.println(calendar1.getTime());
//        Calendar calendar2 = Calendar.getInstance();
//        calendar2.set(2000, Calendar.NOVEMBER, 1);
        System.out.println(getYearOld(calendar1.getTime()));
    }

    private static int getYearOld(Date birthday) {
        // 当前时间
        Calendar curr = Calendar.getInstance();
        curr.set(2005, Calendar.OCTOBER, 2);
//        curr.set(2005, Calendar.OCTOBER, 1);
//        curr.set(2000, Calendar.NOVEMBER, 1);

        // 生日
        Calendar born = Calendar.getInstance();
        born.setTime(birthday);

        // 年龄 = 当前年 - 出生年
        int age = curr.get(Calendar.YEAR) - born.get(Calendar.YEAR);
        if (age <= 0) {
            return 0;
        }

        // 如果当前月份小于出生月份：age - 1
        // 如果当前月份等于出生月份，且当前日小于出生日：age - 1
        int currMonth = curr.get(Calendar.MONTH);
        int currDay = curr.get(Calendar.DAY_OF_MONTH);
        int bornMonth = born.get(Calendar.MONTH);
        int bornDay = born.get(Calendar.DAY_OF_MONTH);
        if ((currMonth < bornMonth) || (currMonth == bornMonth && currDay <= bornDay)) {
            age--;
        }

        return Math.max(age, 0);
    }
}
