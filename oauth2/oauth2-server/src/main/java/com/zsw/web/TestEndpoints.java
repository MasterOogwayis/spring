package com.zsw.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * This is a test endpoint.
 *
 * @author ZhangShaowei on 2018/2/9 9:33
 **/
@RestController
public class TestEndpoints {

    /**
     * @param id
     * @return
     */
    @GetMapping("/product/{id}")
    public String getProduct(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "product id : " + id;
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/order/{id}")
    public String getOrder(@PathVariable String id) {
        //for debug
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "order id : " + id;
    }


    /**
     * @param principal
     * @return
     */
    @GetMapping("user")
    public Principal user(Principal principal) {
        return principal;
    }

}
