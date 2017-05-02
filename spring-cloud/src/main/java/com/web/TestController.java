package com.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2017/4/28 15:25
 */
@RestController
public class TestController {

    @Value("${from}")
    private String from;

    @RequestMapping(value = "/from", method = RequestMethod.POST)
    public String from() {
        return this.from;
    }



}
