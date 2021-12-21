package com.zsw;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ZhangShaowei on 2021/12/21 15:12
 */
@RestController
public class SecurityApi {


    @GetMapping("hello")
    public String hello(@RequestParam("name") String name) {
        return "Hello " + name;
    }

    @GetMapping("user")
    public Object user() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @PreAuthorize("hasRole('admin')")
    @GetMapping("sum")
    public Object set(@RequestParam("a") Integer a, @RequestParam("b") Integer b) {
        return a + b;
    }

    @PreAuthorize("hasRole('user')")
    @GetMapping("helloWorld")
    public Object helloWorld() {
        return "Hello World!";
    }

}
