package com.zsw.base.oauth2.config;

import org.springframework.lang.NonNull;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ZhangShaowei on 2021/6/8 16:00
 */
public interface PasswordEncoderWrapper {

    /**
     * wrap
     *
     * @param source PasswordEncoder
     * @return Wrapper
     */
    @NonNull
    PasswordEncoder wrap(PasswordEncoder source);

}
