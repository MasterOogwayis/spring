package com.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author ZhangShaowei on 2017/6/1 14:43
 */
@Service
public class HystrixService {

    /**
     *
     */
    @Autowired
    private CallDependencyClient callDependencyClient;

    /**
     * @return
     */
    public Integer add() {
        Integer num = this.callDependencyClient.add(1, 2);
        return num;
    }


    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmm");
        Calendar nowCalendar = Calendar.getInstance();
        System.err.println(sdf.format(nowCalendar.getTime()));
    }

}
