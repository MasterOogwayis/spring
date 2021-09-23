package com.zsw.service;

import com.zsw.annotation.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2021/9/23 10:39
 */
@Slf4j
@Component
public class TestThisService {

    @Log
    public String test1(String str) {
        return this.test2(str);
    }


    @Log
    public String test2(String str) {
        System.err.println(str);
        return str;
    }

}
