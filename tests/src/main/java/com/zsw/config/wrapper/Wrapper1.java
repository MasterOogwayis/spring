package com.zsw.config.wrapper;

import com.zsw.config.EncryptTransportPasswordEncoder;
import com.zsw.config.PasswordEncoderWrapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.function.Function;

/**
 * @author ZhangShaowei on 2021/6/15 14:41
 */
@Component
public class Wrapper1 implements PasswordEncoderWrapper {
    @Override
    public PasswordEncoder wrap(PasswordEncoder source) {
        return new EncryptTransportPasswordEncoder(source, Function.identity());
    }
}
