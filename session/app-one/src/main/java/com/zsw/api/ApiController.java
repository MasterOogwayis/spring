package com.zsw.api;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2021/7/8 14:39
 */
@RestController
public class ApiController {

    @GetMapping("hello")
    public String hello() {
        return "Hello World";
    }


    @GetMapping("user")
    public Object user() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

}
