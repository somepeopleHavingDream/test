package org.yangxin.test.jdk8.datetime.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 日期工具类
 *
 * @author yangxin
 * 2021/3/19 11:44
 */
public final class LocalDateUtil {

    /**
     * 获得时间范围内的所有日期
     *
     * @param startDate 起始日期
     * @param endDate 结束日期
     * @return 时间范围内的所有日期
     */
    public static Set<LocalDate> listDaysInRangeExceptWeekend(LocalDate startDate, LocalDate endDate) {
        Set<LocalDate> localDateSet = new HashSet<>();

        if (startDate.compareTo(endDate) > 0) {
            return localDateSet;
        }

        while (true) {
            // 除掉双休日
            if (!Objects.equals(startDate.getDayOfWeek(), DayOfWeek.SATURDAY)
                    && !Objects.equals(startDate.getDayOfWeek(), DayOfWeek.SUNDAY)) {
                localDateSet.add(startDate);
            }

            if (startDate.compareTo(endDate) >= 0) {
                break;
            }

            startDate = startDate.plusDays(1);
        }

        return localDateSet;
    }
}
