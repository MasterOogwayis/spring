package com.demo.spring.dependency.inject;

import com.demo.spring.ioc.overview.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author Shaowei Zhang on 2021/5/3 0:09
 */
@Slf4j
public class InjectionDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(InjectionDemo.class);
        applicationContext.scan(InjectionDemo.class.getPackageName());
        applicationContext.refresh();

        UserHolder userHolder = applicationContext.getBean(UserHolder.class);
        log.info("userHolder: {}, user: {}", userHolder, userHolder.getObject());

        applicationContext.close();

    }

    @Bean
    public User user() {
        User user = User.createUser();
        user.setId(1L);
        user.setAddress("Earth");
        user.setName("autowire");
        return user;
    }

}
