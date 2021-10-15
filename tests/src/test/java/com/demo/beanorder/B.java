package com.demo.beanorder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangShaowei on 2021/10/15 14:50
 */
@Configuration
public class B {

    @Bean
    public C c() {
        return new C();
    }

}
