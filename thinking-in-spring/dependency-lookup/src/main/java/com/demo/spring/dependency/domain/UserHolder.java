package com.demo.spring.dependency.domain;

import com.demo.spring.ioc.overview.domain.User;
import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2021/4/28 15:51
 */
@Component
public class UserHolder implements ObjectHolder<User> {
    @Override
    public User getObject() {
        User user = User.createUser();
        user.setAddress("Mars");
        user.setName("UserHolder");
        user.setId(1L);
        return user;

    }
}
