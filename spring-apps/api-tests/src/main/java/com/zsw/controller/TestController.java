package com.zsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/7/8 9:37
 */
@RequestMapping("get")
@RestController
public class TestController {

    private Environment environment;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping
    public Object get(@RequestParam("key") String key) {
        return this.environment.getProperty(key);
    }


}
