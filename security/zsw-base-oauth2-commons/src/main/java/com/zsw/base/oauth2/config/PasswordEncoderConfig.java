package com.zsw.base.oauth2.config;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ZhangShaowei on 2020/7/2 10:15
 */
@Configuration(proxyBeanMethods = false)
public class PasswordEncoderConfig {

    /**
     * 不用 md5 加密密码了，默认 bcrypt
     *
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder(ObjectProvider<PasswordEncoderWrapper> wrappers) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        for (PasswordEncoderWrapper wrapper : wrappers) {
            passwordEncoder = wrapper.wrap(passwordEncoder);
        }
        return passwordEncoder;
    }

}
