package com.zsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/7/13 10:05
 */
@RequestMapping("config")
@RestController
@EnableConfigurationProperties(RedisProperties.class)
public class ConfigController {

    @Autowired
    private RedisProperties properties;


    @GetMapping
    public Object config() {
        return this.properties;
    }

}
