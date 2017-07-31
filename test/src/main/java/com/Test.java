package com;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ZhangShaowei on 2017/6/27 10:52
 */

public class Test {

    public static void main(String[] args) throws Exception {
//        System.out.println(Integer.MAX_VALUE);
//        Calendar calendar = Calendar.getInstance(Locale.CHINA);
//        Date date = new Date();
//        System.err.println(calendar.get(Calendar.DAY_OF_WEEK) - 1);

        Long num = Long.MAX_VALUE;
        System.err.println(Long.toString(num, 64));
        System.err.println(Long.toHexString(num));
    }


}
