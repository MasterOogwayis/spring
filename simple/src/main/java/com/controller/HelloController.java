package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * HelloController
 *
 * @author ZhangShaowei on 2018/4/26 11:44
 **/
@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(HttpServletRequest request) {
        request.setAttribute("name", "work");
        return "hello";
    }

}
