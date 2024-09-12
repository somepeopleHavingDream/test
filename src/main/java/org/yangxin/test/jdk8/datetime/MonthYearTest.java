package org.yangxin.test.jdk8.datetime;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yangxin
 * 2024/9/12 16:13
 */
@SuppressWarnings("unused")
public class MonthYearTest {

    public static void main(String[] args) {
//        test1();
        test2();
    }

    /**
     * YearMonth 转 Date
     */
    private static void test2() {
        YearMonth yearMonth = YearMonth.of(2023, 9); // 例如：2023年9月

        // 转换为该月的第一天
        LocalDate firstDayOfMonth = yearMonth.atDay(1);

        // 将 LocalDate 转换为 Date
        Date date = Date.from(firstDayOfMonth.atStartOfDay(ZoneId.systemDefault()).toInstant());

        System.out.println(date);
    }

    /**
     * 获得两个日期间的月份
     */
    private static void test1() {
        LocalDate startDate = LocalDate.of(2023, 5, 15);
        LocalDate endDate = LocalDate.of(2024, 2, 10);

        List<YearMonth> months = new ArrayList<>();

        // 将 LocalDate 转换为 YearMonth
        YearMonth startMonth = YearMonth.from(startDate);
        YearMonth endMonth = YearMonth.from(endDate);

        // 遍历起始日期和结束日期之间的所有月份
        while (!startMonth.isAfter(endMonth)) {
            months.add(startMonth);
            startMonth = startMonth.plusMonths(1);  // 加1个月
        }
        months.forEach(System.out::println);  // 输出所有月份

    }
}
