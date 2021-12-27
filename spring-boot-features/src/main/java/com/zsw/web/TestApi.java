package com.zsw.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;

/**
 * @author ZhangShaowei on 2021/12/16 14:45
 */
@RequestMapping("test")
@RestController
public class TestApi {

    @Autowired
    private ServletRequest request;

    @GetMapping
    public Object get(@RequestParam("name") String name) {
        return request.getParameter(name);
    }

}
