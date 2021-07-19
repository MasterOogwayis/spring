package com.zsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 这里有个坑， 如果使用注解 @EnableRedisHttpSession 这配置文件无法生效，只能在注解里面写配置
 *
 * @author ZhangShaowei on 2021/7/8 13:48
 */
@EnableWebSecurity
//@EnableRedisHttpSession
@SpringBootApplication
public class AppTwo {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(AppTwo.class, args);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
