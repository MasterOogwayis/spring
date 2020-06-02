package com.zsw.test.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/5/11 14:18
 */
@Slf4j
@RequestMapping("simple")
@RestController
@Transactional(rollbackFor = Exception.class)
public class HelloWorldApi {

    @Autowired
    private TestService testService;

    @GetMapping("hello")
//    @Cacheable(value = "test", key = "#name", condition = "#result != null ")
    public String hello(@RequestParam("name") String name, @RequestHeader(value = "attr", required = false) String attr) {
        log.info("header: attr={}", attr);
        return "Hello " + name;
    }

//    @GetMapping("hello1")
//    @Cacheable(value = "test", key = "#name", condition = "#result != null ")
//    private String hello1(@RequestParam("name") String name, @RequestHeader(value = "attr", required = false) String attr) {
//        log.info("header: attr={}", attr);
//        return "Hello1 " + name;
//    }


}
