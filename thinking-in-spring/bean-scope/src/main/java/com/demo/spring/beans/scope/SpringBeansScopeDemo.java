package com.demo.spring.beans.scope;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

/**
 * @author ZhangShaowei on 2021/5/8 17:08
 */
@Slf4j
public class SpringBeansScopeDemo {


    @Autowired
    private Customer c1;

    @Autowired
    private Customer c2;

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(SpringBeansScopeDemo.class);
        applicationContext.refresh();

        SpringBeansScopeDemo bean = applicationContext.getBean(SpringBeansScopeDemo.class);

        log.info("c1: {}", bean.c1);
        log.info("c2: {}", bean.c2);

        applicationContext.close();
    }

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    public Customer customer() {
        return new Customer();
    }


}
