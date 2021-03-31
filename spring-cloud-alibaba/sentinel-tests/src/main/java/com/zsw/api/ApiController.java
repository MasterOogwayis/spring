package com.zsw.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/9/18 11:07
 */
@RequestMapping("api")
@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("hello")
    public Object get(@RequestParam("name") String name) {
        return this.apiService.hello(name);
    }

}