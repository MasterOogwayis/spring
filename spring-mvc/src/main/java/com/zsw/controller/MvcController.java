package com.zsw.controller;

import com.zsw.service.MvcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/4/15 12:13
 **/
@RequestMapping("test")
@RestController
public class MvcController {

    @Autowired
    private MvcService mvcService;

    @GetMapping
    public Object test() {
        this.mvcService.sayHello("World!");
        return "success";
    }

}
