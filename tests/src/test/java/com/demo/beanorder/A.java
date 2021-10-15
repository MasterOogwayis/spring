package com.demo.beanorder;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangShaowei on 2021/10/15 14:49
 */
@ConditionalOnBean(C.class)
@Configuration
public class A {

    @Bean
    public D d() {
        return new D();
    }

}
