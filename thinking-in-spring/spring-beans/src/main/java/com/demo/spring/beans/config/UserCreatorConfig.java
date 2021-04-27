package com.demo.spring.beans.config;

import com.demo.spring.ioc.overview.domain.User;
import org.springframework.context.annotation.Bean;

/**
 * @author ZhangShaowei on 2021/4/27 10:06
 */
public class UserCreatorConfig {

    @Bean
    public User user() {
        User user = new User();
        user.setId(Long.MAX_VALUE);
        user.setName("This is a test.");
        user.setAddress("Mars");
        return user;
    }

}
