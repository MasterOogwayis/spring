package com.demo.spring.annotation;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author ZhangShaowei on 2021/11/16 9:27
 */
@Slf4j
@Configuration
public class ProfileDemo {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(ProfileDemo.class);

        applicationContext.getEnvironment().setDefaultProfiles("two");

        applicationContext.refresh();

        Number number = applicationContext.getBean(Number.class);

        log.info("number = {}", number);

        applicationContext.close();

    }


    @Profile("one")
    @Bean("one")
    public Integer one() {
        return 1;
    }

//    @Profile("two")
    @Bean("two")
    @Conditional(EvenProfileConditional.class)
    public Integer two() {
        return 2;
    }

}
