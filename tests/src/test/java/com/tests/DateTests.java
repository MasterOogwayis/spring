package com.tests;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.YearMonth;

/**
 * @author Shaowei Zhang on 2022/3/20 12:19
 */
public class DateTests {

    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2022, 3, 20);

        System.out.println(localDate.getDayOfWeek());
    }

}
