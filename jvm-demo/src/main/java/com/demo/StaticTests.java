package com.demo;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.Delegate;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author ZhangShaowei on 2019/2/27 14:15
 **/
@Slf4j
public class StaticTests {


    @SneakyThrows
    public static void main(String[] args) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

//        LocalDate localDate = LocalDate.now().withDayOfMonth(24);
//        LocalDateTime endDate = localDate.plusDays(-2).atStartOfDay();
//        LocalDateTime startDate = endDate.plusDays(-7);
//        System.out.println(startDate.format(formatter));
//        System.out.println(endDate.format(formatter));

//        YearMonth month = YearMonth.now();
//        LocalDateTime startDate = month.plusMonths(-1).atDay(22).atStartOfDay();
//        LocalDateTime endDate = month.atDay(1).atStartOfDay();
//        System.out.println(startDate.format(formatter));
//        System.err.println(endDate.format(formatter));

        System.out.println(new String("123456789".substring(0, 5).getBytes(), "utf-8"));


    }


    @Test
    public void test3() throws IllegalAccessException {
        Map<String, String> map = new HashMap<>();
        map.put("1", "1");
        System.out.println(map.get("1"));
    }



}
