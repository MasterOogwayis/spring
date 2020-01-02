package com;

import java.math.BigDecimal;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author ZhangShaowei on 2019/12/30 13:47
 */
public class MapperUtils {

    public static final BigDecimal DIVISOR = BigDecimal.valueOf(100);

    /**
     * yyyy-MM-dd
     */
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());

    /**
     * yyyy-MM-dd HH:mm:ss
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(ZoneId.systemDefault());


    @FenToYuanHalfUp
    public static String fenToYuanHalfUp(Number number) {
        return BigDecimal.valueOf(number.doubleValue())
                .divide(DIVISOR, 2, BigDecimal.ROUND_HALF_UP).toString();
    }


    @FenToYuan
    public static String fenToYuan(Number number) {
        return number.toString();
    }


    /**
     * to default format yyyy-MM-dd
     *
     * @param date Date
     * @return String
     */
    @DateFormat
    public static String dateFormat(final Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate().format(DATE_FORMAT);
    }


    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date Date
     * @return String
     */
    @DateTimeFormat
    public static String dateTimeFormat(final Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).format(DATE_TIME_FORMATTER);
    }

}
