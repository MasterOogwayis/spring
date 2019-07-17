package com.zsw.orm.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * jdk8 的date处理  都是线程安全的处理方式 效率比SimpleDateFormat高
 * <p>
 * 如果是JDK8的应用，可以使用Instant代替Date，LocalDateTime代替Calendar，DateTimeFormatter代替Simpledateformatter
 * 官方给出的解释：simple beautiful strong immutable thread-safe。
 *
 * @author ZhangShaowei on 2018/1/15 14:10
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {

    /**
     * Number of seconds in a standard minute.
     */
    public static final long SECOND_PER_MINUTE = 60;
    /**
     * Number of seconds in a standard hour.
     */
    public static final long SECOND_PER_HOUR = 60 * SECOND_PER_MINUTE;
    /**
     * Number of seconds in a standard day.
     */
    public static final long SECOND_PER_DAY = 24 * SECOND_PER_HOUR;

    /**
     * yyyy-MM-dd
     * <p>
     * DateTimeFormatter.ofPattern(BaseConstant.DATE_FORMAT)
     */
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;
    /**
     * yyyy年MM月dd日
     */
    private static final DateTimeFormatter DATE_FORMAT_ZH = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
    /**
     *
     */
    private static final DateTimeFormatter TIMESTAMP_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * HH:mm:ss
     */
    private static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");


    /**
     * yyyyMMddHHmmss
     */
    private static final DateTimeFormatter TIME_LABEL_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    /**
     * 'yyyy-MM-dd HH:mm:ss'
     *
     * @param date Date
     * @return String
     */
    public static String dateTimeFormat(final Date date) {
        return dateTimeFormat(zonedDateTime(date).toLocalDateTime());
    }

    /**
     * @param localDateTime LocalDateTime
     * @return
     */
    public static String dateTimeFormat(final LocalDateTime localDateTime) {
        return localDateTime.format(TIMESTAMP_FORMAT);
    }


    /**
     * to default today's format 'HH:mm:ss'
     *
     * @param date Date
     * @return String
     */
    public static String timeFormat(final Date date) {
        return zonedDateTime(date).toLocalDate().format(TIME_FORMAT);
    }

    /**
     * to default format yyyy-MM-dd
     *
     * @param date Date
     * @return String
     */
    public static String dateFormat(final Date date) {
        return dateFormat(zonedDateTime(date).toLocalDate());
    }

    /**
     * @param localDate LocalDate
     * @return
     */
    public static String dateFormat(final LocalDate localDate) {
        return localDate.format(DATE_FORMAT);
    }


    /**
     * to default format yyyy-MM-dd
     *
     * @param date Date
     * @return String
     */
    public static String dateFormatZh(final Date date) {
        return dateFormatZh(zonedDateTime(date).toLocalDate());
    }

    /**
     * @param localDate LocalDate
     * @return
     */
    public static String dateFormatZh(final LocalDate localDate) {
        return localDate.format(DATE_FORMAT_ZH);
    }

    /**
     * 当前时间戳
     * <p>
     * yyyyMMddHHmmss
     *
     * @return
     */
    public static String timeLabel() {
        return LocalDateTime.now().format(TIME_LABEL_FORMAT);
    }

    /**
     * yyyy-MM-dd
     *
     * @param date String
     * @return Date
     */
    public static Date parseDate(final String date) {
        return Date.from(localDate(date).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 'HH:mm:ss'
     *
     * @param time String
     * @return Date
     */
    public static Date parseTime(final String time) {
        return Date.from(
                LocalDateTime.of(
                        LocalDate.now(),
                        localTime(time)
                ).atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 'yyyy-MM-dd HH:mm:ss'
     *
     * @param dateTime String
     * @return Date
     */
    public static Date parseDateTime(final String dateTime) {
        return Date.from(localDateTime(dateTime).atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * 判断是否过期  date < now
     *
     * @param date 日期
     * @return true or false
     */
    public static boolean before(final Date date) {
        return zonedDateTime(date).toLocalDate().isBefore(LocalDate.now());
    }

    /**
     * 判断2个日期的大小
     *
     * @param date1 Date
     * @param date2 Date
     * @return date1 < date2
     */
    public static boolean before(final Date date1, final Date date2) {
        LocalDate localDate1 = zonedDateTime(date1).toLocalDate();
        LocalDate localDate2 = zonedDateTime(date2).toLocalDate();
        return localDate1.isBefore(localDate2);
    }

    /**
     * 当前系统时间 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    public static String now() {
        return dateTimeFormat(LocalDateTime.now());
    }

    /**
     * 当前系统时间 yyyy-MM-dd 00:00:00
     *
     * @return
     */
    public static Date systemDate() {
        return Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * yyyy-MM-dd
     *
     * @param date String
     * @return LocalDate
     */
    public static LocalDate localDate(final String date) {
        return LocalDate.parse(date, DATE_FORMAT);
    }


    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date String
     * @return LocalDateTime
     */
    public static LocalDateTime localDateTime(final String date) {
        return LocalDateTime.parse(date, TIMESTAMP_FORMAT);
    }

    /**
     * HH:mm:ss
     *
     * @param time String
     * @return
     */
    public static LocalTime localTime(final String time) {
        return LocalTime.parse(time, TIME_FORMAT);
    }

    /**
     * to ZonedDateTime
     *
     * @param date Date
     * @return
     */
    public static ZonedDateTime zonedDateTime(final Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault());
    }

    /**
     * 忽略当前日期的时间部分返回 每天的最早时间
     *
     * @param date Date
     * @return
     */
    public static Date startOfDay(final Date date) {
        return Date.from(zonedDateTime(date).toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 计算到现在的时差
     *
     * @param date Date
     * @return 1天2小时7分钟17秒前 or 1天2小时7分钟17秒前后
     */
    public static String dateTimeString(final Date date) {
        return dateTimeString(zonedDateTime(date).toLocalDateTime());
    }

    /**
     * 计算到现在的时差
     *
     * @param localDateTime LocalDateTime
     * @return
     */
    public static String dateTimeString(final LocalDateTime localDateTime) {
        LocalDateTime now = LocalDateTime.now();
        boolean isBefore = localDateTime.isBefore(now);

        StringBuilder sb = new StringBuilder();
        // 计算日期差
        Period period;
        // 计算时间差
        Duration duration;
        // 小于当前时间 由于有纳秒存在 所以不会出现相等的情况
        if (isBefore) {
            // 处理日期
            period = Period.between(localDateTime.toLocalDate(), now.toLocalDate());
            duration = Duration.between(localDateTime.toLocalTime(), now.toLocalTime());
        } else {
            // 当前时间之后
            period = Period.between(now.toLocalDate(), localDateTime.toLocalDate());
            duration = Duration.between(now.toLocalTime(), localDateTime.toLocalTime());
        }

        if (duration.isNegative()) {
            // 跨天导致时间为负数 日期差-1 时间差+1
            period = period.plusDays(-1);
            duration = duration.plusDays(1);
        }
        if (period.isZero() && duration.getSeconds() == 0) {
            sb.append("1秒");
        } else {
            sb.append(period.getYears() == 0 ? "" : (period.getYears() + "年"));
            sb.append(period.getMonths() == 0 ? "" : (period.getMonths() + "月"));
            sb.append(period.getDays() == 0 ? "" : (period.getDays() + "天"));

            long hours = duration.getSeconds() / SECOND_PER_HOUR;
            long minutes = duration.getSeconds() % SECOND_PER_HOUR / SECOND_PER_MINUTE;
            long seconds = duration.getSeconds() % SECOND_PER_HOUR % SECOND_PER_MINUTE;

            sb.append(hours == 0 ? "" : (hours + "小时"));
            sb.append(minutes == 0 ? "" : (minutes + "分钟"));
            sb.append(seconds == 0 ? "" : (seconds + "秒"));
        }
        sb.append(isBefore ? "前" : "后");

        return sb.toString();
    }

    /**
     * long to String
     *
     * @param time
     * @return
     */
    public static String dateTimeFormat(Long time) {
        return dateTimeFormat(LocalDateTime.ofEpochSecond(
                time / 1000L,
                0,
                ZoneOffset.ofHours(8)));
    }

    public static Date format(String dateStr, String pattern) {
        LocalDateTime parse = LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
        return Date.from(parse.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String[] args) {
        Date format = format("11:11:11", "HH:mm:ss");
        System.out.println(format);
    }


}
