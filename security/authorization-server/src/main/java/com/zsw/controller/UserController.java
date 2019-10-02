package com.zsw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author ZhangShaowei on 2019/10/2 17:23
 **/
@RequestMapping("user")
@RestController
public class UserController {

    @RequestMapping
    public Principal principal(Principal principal) {
        return principal;
    }

}
