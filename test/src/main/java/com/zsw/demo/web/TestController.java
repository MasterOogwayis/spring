package com.zsw.demo.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/10/28 10:34
 **/
@Slf4j
@RequestMapping("test")
@RestController
public class TestController {

    @GetMapping
    public Object ping() {
        log.info("info");
        log.debug("debug");
        log.error("error");
        return "pong";
    }


}
