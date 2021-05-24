package com.zsw.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

/**
 * @author ZhangShaowei on 2021/1/13 13:01
 */
//@RestController
//@RequestMapping("hello")
public class ApiController {

//    @Autowired
//    private ISayHelloService sayHelloService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private CacheManager cacheManager;

    @GetMapping
    public String hello(@RequestParam String name) {
        return this.cacheService.hello(name);
    }

    @GetMapping("get")
    public String get(@RequestParam String key) {
        return Optional.ofNullable(this.cacheManager.getCache("hello"))
                .map(cache -> cache.get(key))
                .map(Cache.ValueWrapper::get)
                .map(Object::toString)
                .orElse("not found!");
    }

}
