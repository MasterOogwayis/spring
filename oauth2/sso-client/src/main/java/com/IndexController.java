package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author ZhangShaowei on 2017/12/26 9:46
 */
@RestController
public class IndexController {


    /**
     * @param principal
     * @return
     */
    @GetMapping("user")
    public Principal user(Principal principal) {
        return principal;
    }

    @PostMapping("hello")
    public String hello(String name) {
        return "hello " + name;
    }


}
