package com;

import org.springframework.cache.annotation.CustomCacheable;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2019/6/13 17:15
 **/
@Service
public class SimpleService {

    @CustomCacheable(value = "names", key = "'test:' + #name")
    public String get(String name) {
        return name;
    }


}
