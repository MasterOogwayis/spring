package com.zsw.base.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ZhangShaowei on 2020/7/2 10:15
 */
@Configuration
public class PasswordEncoderConfig {

    /**
     * 不用 md5 加密密码了，默认 bcrypt
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new PasswordEncoderConfig().passwordEncoder();
        String encode = passwordEncoder.encode("backend-api");
        System.out.println(encode);
    }

}
