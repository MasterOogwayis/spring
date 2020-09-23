package com.zsw.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/9/23 12:07
 */
@RequestMapping("elastic")
@RestController
public class ElasticApi {


    @GetMapping
    public String hello(@RequestParam("name") String name) {
        return "Hello " + name;
    }


}
