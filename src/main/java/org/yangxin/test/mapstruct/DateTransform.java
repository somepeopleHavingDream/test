package org.yangxin.test.mapstruct;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author yangxin
 * 2022/3/7 14:56
 */
public class DateTransform {

    public static LocalDateTime strToDate(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(str, formatter);
    }
}
