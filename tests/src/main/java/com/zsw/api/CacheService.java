package com.zsw.api;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author ZhangShaowei on 2021/5/11 10:27
 */
@Service
public class CacheService {

    @Cacheable(value = "hello", key = "#name")
    public String hello(String name) {
        return "hello" + name;
    }

}
