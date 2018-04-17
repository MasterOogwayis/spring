package com.zsw.api.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author ZhangShaowei on 2017/11/10 14:28
 */
@RestController
public class SecurityController {


    /**
     * @param principal
     * @return UserRole
     */
    @GetMapping("user")
    public Principal user(final Principal principal) {
        return principal;
    }


}
