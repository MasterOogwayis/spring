package com;

import org.springframework.stereotype.Service;

import java.time.YearMonth;

/**
 * @author ZhangShaowei on 2019/6/13 17:15
 **/
@Service
public class SimpleService {

    public String getDayOfMonth(int year, int month) {
        return YearMonth.of(year, month).atEndOfMonth().getDayOfMonth() + "";
    }

    public String sayHello(String name) {
        String word = "Hello " + name;
        System.out.println(word);
        return word;
    }


}
