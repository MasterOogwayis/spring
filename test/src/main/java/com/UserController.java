package com;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * This is UserController
 *
 * @author ZhangShaowei on 2018/1/19 11:26
 **/
@RestController
public class UserController {

    /**
     * @param principal Principal
     * @return Principal
     */
    @GetMapping("user")
    public Principal user(final Principal principal) {
        return principal;
    }


}
