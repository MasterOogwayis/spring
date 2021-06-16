package com.zsw.base.oauth2.config;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author ZhangShaowei on 2021/6/16 9:45
 */
@Component
public class EncryptWrapper implements PasswordEncoderWrapper {
    @Override
    public PasswordEncoder wrap(PasswordEncoder source) {
        return new EncryptTransportPasswordEncoder(source, this::decode);
    }


    private String decode(CharSequence string) {
        return string.toString();
    }
}
