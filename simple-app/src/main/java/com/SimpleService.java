package com;

import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2019/6/13 17:15
 **/
@Service
public class SimpleService {

    //    @CustomCacheable(value = "names", key = "'test:' + #name", expire = 15, timeUnit = TimeUnit.MINUTES)
    public String get(String name) {
        return name;
    }


}
