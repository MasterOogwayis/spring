package com.zsw.orm.utils;

import org.springframework.util.StringUtils;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * jdk8 的date处理  都是线程安全的处理方式 效率比SimpleDateFormat高
 * <p>
 * 如果是JDK8的应用，可以使用Instant代替Date，LocalDateTime代替Calendar，DateTimeFormatter 代替 SimpleDateFormatter
 * 官方给出的解释：simple beautiful strong immutable thread-safe。
 * <p>
 * LocalDate.of(2020, 5, 1).with(TemporalAdjusters.nextOrSame(DayOfWeek.THURSDAY))
 *
 * @author ZhangShaowei on 2019/11/15 14:10
 */
public class DateTimeUtils {

    private static final Map<String, DateTimeFormatter> FORMATTERS = new ConcurrentHashMap<>();

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
    private static final DateTimeFormatter DATE_FORMAT = getFormatter("yyyy-MM-dd");
    /**
     * yyyy年MM月dd日
     */
    private static final DateTimeFormatter DATE_FORMAT_ZH = getFormatter("yyyy年MM月dd日");
    /**
     *
     */
    private static final DateTimeFormatter TIMESTAMP_FORMAT = getFormatter("yyyy-MM-dd HH:mm:ss");


    /**
     * yyyyMMddHHmmss
     */
    private static final DateTimeFormatter TIME_LABEL_FORMAT = getFormatter("yyyyMMddHHmmss");

    /**
     * 'yyyy-MM-dd HH:mm:ss'
     *
     * @param date Date
     * @return String
     */
    public static String dateTimeFormat(final Date date) {
        return dateTimeFormat(zonedDateTime(date));
    }

    /**
     * @param zonedDateTime ZonedDateTime
     * @return
     */
    public static String dateTimeFormat(final ZonedDateTime zonedDateTime) {
        return zonedDateTime.format(TIMESTAMP_FORMAT);
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

    public static String dateFormat(final Object object) {
        if (object instanceof Date) {
            return dateFormat((Date) object);
        } else if (object instanceof Number) {
            return dateFormat(new Date(((Number) object).longValue()));
        }
        throw new IllegalArgumentException("Cannot format given Object as a Date");
    }

    public static String dateTimeFormat(final Object object) {
        if (object instanceof Date) {
            return dateTimeFormat((Date) object);
        } else if (object instanceof Number) {
            return dateTimeFormat(new Date(((Number) object).longValue()));
        }
        throw new IllegalArgumentException("Cannot format given Object as a Date");
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
        return ZonedDateTime.now(ZoneId.systemDefault()).format(TIME_LABEL_FORMAT);
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
                ZonedDateTime.of(
                        LocalDate.now(ZoneId.systemDefault()),
                        localTime(time),
                        ZoneId.systemDefault()
                ).toInstant()
        );
    }

    /**
     * 'yyyy-MM-dd HH:mm:ss'
     *
     * @param dateTime String
     * @return Date
     */
    public static Date parseDateTime(final String dateTime) {
        return Date.from(zonedDateTime(dateTime).toInstant());
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
        return dateTimeFormat(ZonedDateTime.now(ZoneId.systemDefault()));
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
        return LocalDate.parse(date);
    }


    /**
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date String
     * @return LocalDateTime
     */
    public static ZonedDateTime zonedDateTime(final String date) {
        return ZonedDateTime.parse(date, TIMESTAMP_FORMAT);
    }

    /**
     * HH:mm or HH:mm:ss
     *
     * @param time String
     * @return
     */
    public static LocalTime localTime(final String time) {
        return LocalTime.parse(time);
    }

    /**
     * to ZonedDateTime
     *
     * @param date Date
     * @return
     */
    public static ZonedDateTime zonedDateTime(final Date date) {
        return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 今天 00:00:00
     *
     * @return
     */
    public static Date startOfToday() {
        return startOfDay(new Date());
    }

    public static Date startOfDay(Date date) {
        return startOfDay(date, 0);
    }

    /**
     * @param date   Date
     * @param offset offset
     * @return yyyy-MM-dd 00:00:00
     */
    public static Date startOfDay(Date date, int offset) {
        return Date.from(
                zonedDateTime(date)
                        .toLocalDate()
                        .plusDays(offset)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()
        );
    }

    /**
     * @param date Date
     * @return yyyy-MM-dd 23:59:59.9999999
     */
    public static Date endOfDay(Date date) {
        return endOfDay(date, 0);
    }

    public static Date endOfDay(Date date, int offset) {
        return Date.from(
                zonedDateTime(date)
                        .toLocalDate()
                        .plusDays(offset)
                        .atTime(LocalTime.MAX)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()
        );
    }


    /**
     * 计算到现在的时差
     *
     * @param date Date
     * @return 1天2小时7分钟17秒前 or 1天2小时7分钟17秒前后
     */
    public static String dateTimeString(final Date date) {
        return dateTimeString(zonedDateTime(date));
    }

    public static Date plusDays(final Date date, int day) {
        return Date.from(zonedDateTime(date).plusDays(day).toInstant());
    }

    public static Date minusDays(final Date date, int day) {
        return Date.from(zonedDateTime(date).minusDays(day).toInstant());
    }


    /**
     * 计算到现在的时差
     *
     * @param zonedDateTime ZonedDateTime
     * @return
     */
    public static String dateTimeString(final ZonedDateTime zonedDateTime) {
        ZonedDateTime now = ZonedDateTime.now(ZoneId.systemDefault());
        boolean isBefore = zonedDateTime.isBefore(now);

        StringBuilder sb = new StringBuilder();
        // 计算日期差
        Period period;
        // 计算时间差
        Duration duration;
        // 小于当前时间 由于有纳秒存在 所以不会出现相等的情况
        if (isBefore) {
            // 处理日期
            period = Period.between(zonedDateTime.toLocalDate(), now.toLocalDate());
            duration = Duration.between(zonedDateTime.toLocalTime(), now.toLocalTime());
        } else {
            // 当前时间之后
            period = Period.between(now.toLocalDate(), zonedDateTime.toLocalDate());
            duration = Duration.between(now.toLocalTime(), zonedDateTime.toLocalTime());
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
        return dateTimeFormat(new Date(time));
    }

    public static String dateFormat(Long time) {
        return dateFormat(new Date(time));
    }

    public static DateTimeFormatter getFormatter(String pattern) {
        return Optional.ofNullable(pattern)
                .filter(StringUtils::hasText)
                .map(p -> FORMATTERS.computeIfAbsent(pattern, format -> DateTimeFormatter.ofPattern(format).withZone(ZoneId.systemDefault())))
                .orElse(DATE_FORMAT);
    }

    public static String format(Date date, String pattern) {
        return zonedDateTime(date).format(getFormatter(pattern));
    }

    public static Date parse(String dateTime, String pattern) {
        TemporalAccessor temporalAccessor = getFormatter(pattern).parse(dateTime);
        if (temporalAccessor.isSupported(ChronoField.NANO_OF_DAY)) {
            return Date.from(Instant.from(temporalAccessor));
        }
        return Date.from(LocalDate.from(temporalAccessor).atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static long millisecondsFromTomorrow() {
        return LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli()
                - System.currentTimeMillis();
    }

    /**
     * 设定日期的时间
     *
     * @param date Date
     * @param time support 'HH:mm:ss' or 'HH:mm'
     * @return
     */
    public static Date setTime(Date date, String time) {
        return Date.from(
                zonedDateTime(date).toLocalDate().atTime(localTime(time)).atZone(ZoneId.systemDefault()).toInstant()
        );
    }

    /**
     * 获取 time
     *
     * @param date        Date
     * @param withSeconds 是否带秒
     * @return HH:mm:ss or HH:mm
     */
    public static String getTime(Date date, boolean withSeconds) {
        LocalTime localTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        if (withSeconds) {
            return localTime.toString();
        }
        return localTime.withSecond(0).toString();
    }

    /**
     * HH:mm:ss & HH:mm格式的时间加减分钟数
     *
     * @param time
     * @param minutes
     * @return
     */
    public static String plusMinutes(String time, int minutes) {
        return LocalTime.parse(time).plusMinutes(minutes).toString();
    }


    private static final String[] WEEK_OF_DAYS = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};

    /**
     * @param date
     * @return
     */
    public static String dayOfWeek(Date date) {
        int day = Optional.ofNullable(date)
                .map(Date::toInstant)
                .map(instant -> instant.atZone(ZoneId.systemDefault()))
                .map(ZonedDateTime::getDayOfWeek)
                .map(DayOfWeek::getValue)
                .orElseGet(() -> ZonedDateTime.now(ZoneId.systemDefault()).getDayOfWeek().getValue());
        return WEEK_OF_DAYS[day - 1];
    }


    /**
     * @param begin HH:mm:ss or HH:mm
     * @param end   HH:mm:ss or HH:mm
     * @return HH:mm - HH:mm
     */
    public static String spliceWithoutSeconds(String begin, String end) {
        //开始时间和结束时间相同的情况只显示一个时间
        if (begin.equals(end)) {
            return LocalTime.parse(begin).withSecond(0).toString();
        } else {
            return LocalTime.parse(begin).withSecond(0).toString() + "-" + LocalTime.parse(end).withSecond(0).toString();
        }
    }

}
