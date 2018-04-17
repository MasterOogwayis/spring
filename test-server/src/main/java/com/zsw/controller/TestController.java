package com.zsw.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * test
 *
 * @author ZhangShaowei on 2018/4/13 14:25
 **/
@RestController
public class TestController {

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);

    /**
     * @return
     */
    @PostMapping("test")
    public String test(@RequestBody User user) {
        logger.info("user: {}", user);
        return "{\"success\":true}";
    }


}
