package com.demo.spring.dependency.inject;

import com.demo.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Shaowei Zhang on 2021/5/3 0:11
 */
@Component
public class UserHolder implements ObjectHolder<User> {

//    @Lazy
    @Autowired
    private User user;

    @Override
    public User getObject() {
        return this.user;
    }
}
