package com.zsw.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Shaowei Zhang on 2019/4/24 1:14
 **/
@RequestMapping("customer")
@RestController
public class MyBatisTempController {

    @GetMapping
    public Object findAll() {
        return null;
    }


}
