package com.demo.spring.ioc.overview;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangShaowei on 2021/4/23 15:36
 */
@Configuration
public class IocConfig {

    @Bean
    public String name() {
        return "Shaowei Zhang";
    }

    @Bean
    public Long id() {
        return 1L;
    }

}
