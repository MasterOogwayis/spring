package com.demo.spring.beans.factory;

import com.demo.spring.ioc.overview.domain.User;
import org.springframework.beans.factory.ObjectFactory;


/**
 * User 工厂
 *
 * @author ZhangShaowei on 2021/4/27 10:11
 */
public interface UserFactory extends ObjectFactory<User> {

    @Override
    default User getObject() {
        return User.createUser();
    }

}
