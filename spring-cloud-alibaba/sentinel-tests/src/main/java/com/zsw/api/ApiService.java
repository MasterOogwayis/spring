package com.zsw.api;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2020/9/21 13:13
 */
@Service
public class ApiService {

    @SentinelResource("hello")
    public String hello(String name) {
        return "Hello " + name;
    }

}
