package com.zsw.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author ZhangShaowei on 2019/10/2 16:37
 **/
@RequestMapping("user")
@RestController
public class UserController {

    @GetMapping
    public Principal principal(Principal principal) {
        return principal;
    }

}
