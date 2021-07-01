package com.zsw.service;

import com.zsw.log.catchlog.CatchAndLog;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * @author ZhangShaowei on 2021/1/13 13:00
 */
@CatchAndLog
@Service
public class SayHelloServiceImpl implements ISayHelloService {
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }


    @Override
    public Object order(String user) {
        return Collections.singletonMap("user", user);
    }
}
