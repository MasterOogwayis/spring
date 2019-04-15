package com.zsw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/4/15 12:13
 **/
@RequestMapping("test")
@RestController
public class MvcController {

    @GetMapping
    public Object test() {
        return "success";
    }

}
