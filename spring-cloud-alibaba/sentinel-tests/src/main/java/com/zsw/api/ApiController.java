package com.zsw.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2020/9/18 11:07
 */
@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("trigger")
    public Object trigger() {
        this.apiService.trigger();
        return "success";
    }

    @GetMapping("hello")
    public Object hello() {
        return this.apiService.hello("name");
    }

}
