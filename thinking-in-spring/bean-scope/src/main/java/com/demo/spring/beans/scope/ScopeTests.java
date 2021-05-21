package com.demo.spring.beans.scope;

import com.demo.spring.ioc.overview.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.UUID;

/**
 * @author ZhangShaowei on 2021/5/21 13:47
 */
@Slf4j
@Configuration
public class ScopeTests {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.addBeanFactoryPostProcessor(beanFactory -> {
            beanFactory.registerScope(ThreadLocalScope.SCOPE_NAME, new ThreadLocalScope());
        });
        applicationContext.register(ScopeTests.class);
        applicationContext.refresh();

        log.info("user={}", applicationContext.getBean(User.class));
        log.info("user={}", applicationContext.getBean(User.class));
        log.info("user={}", applicationContext.getBean(User.class));

        new Thread(() -> {
            log.info("thread 1 user={}", applicationContext.getBean(User.class));
        }).start();
        new Thread(() -> {
            log.info("thread 2 user={}", applicationContext.getBean(User.class));
        }).start();
        new Thread(() -> {
            log.info("thread 3 user={}", applicationContext.getBean(User.class));
        }).start();


        applicationContext.close();


    }


    @Scope(ThreadLocalScope.SCOPE_NAME)
    @Bean
    public User user() {
        User user = User.createUser();
        user.setName(UUID.randomUUID().toString());
        return user;
    }


}
