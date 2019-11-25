package com.zsw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2019/11/18 11:16
 **/
@RequestMapping("other")
@RestController
public class OtherController {

    @RequestMapping("get")
    public Object get() {
        return "success";
    }

    @RequestMapping
    public Object find() {
        return "list";
    }


}
