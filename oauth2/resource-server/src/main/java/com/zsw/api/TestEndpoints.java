package com.zsw.api;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestEndpoints
 *
 * @author ZhangShaowei on 2018/5/10 15:55
 **/
@RestController
@RequestMapping("resource")
public class TestEndpoints {


    @GetMapping("get")
    public String getProduct(String id) {
        //for debug
        return "get id : " + id;
    }

    @GetMapping("/save")
    public String getOrder(String id) {
        //for debug
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "save id : " + id;
    }

}
