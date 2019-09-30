package com.zsw.demo.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author ZhangShaowei on 2019/9/29 14:37
 **/
@RequestMapping("test")
@RestController
public class SecurityController {

    @GetMapping("resource")
    public Object resource() {
        return "resource";
    }


    @GetMapping("user")
    public Object user(Principal principal) {
        return principal;
    }


}
