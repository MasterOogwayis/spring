package com.demo.spring.beans.factory;

import com.demo.spring.ioc.overview.domain.User;

/**
 * @author ZhangShaowei on 2021/4/27 11:25
 */
public class CustomUserFactory implements UserFactory {

    @Override
    public User getObject() {
        User user = User.createUser();
        user.setName("customer");
        user.setAddress("Mars");
        user.setId(Long.MIN_VALUE);
        return user;
    }
}
