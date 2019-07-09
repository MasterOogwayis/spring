package com.test.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/6/21 11:32
 **/
@RequestMapping("work")
@RestController
public class WorkController {

    @Autowired
    private DoSomeThingService service;

    @GetMapping
    public Object work() {
        return service.get("work");
    }

}
