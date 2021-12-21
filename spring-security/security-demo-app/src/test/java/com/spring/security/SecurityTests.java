package com.spring.security;

import org.junit.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author ZhangShaowei on 2021/12/20 13:34
 */
public class SecurityTests {

    @Test
    public void test() {
        UserDetails build = User.withDefaultPasswordEncoder()
                .username("root")
                .password("root")
                .roles("admin")
                .build();

    }
    
}
