package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/3/13 14:22
 **/
@RestController
@RequestMapping("test")
public class TestController {

    @Autowired
    TestProperties properties;

    @GetMapping
    public Object test() {
        return this.properties;
    }


}
