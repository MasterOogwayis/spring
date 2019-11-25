package com.zsw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/11/18 11:15
 **/
@RequestMapping("ignore")
@RestController
public class IgnoredController {

    @RequestMapping("get")
    public Object get() {
        return "success";
    }

    @RequestMapping
    public Object find() {
        return "list";
    }

}
