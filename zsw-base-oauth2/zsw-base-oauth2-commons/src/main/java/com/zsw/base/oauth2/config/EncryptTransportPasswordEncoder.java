package com.zsw.base.oauth2.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.function.Function;

/**
 * @author ZhangShaowei on 2021/6/8 15:29
 */
@Getter
@AllArgsConstructor
public class EncryptTransportPasswordEncoder implements PasswordEncoder {

    private final PasswordEncoder target;

    private final Function<CharSequence, CharSequence> decoder;

    @Override
    public String encode(CharSequence rawPassword) {
        return this.target.encode(this.decoder.apply(rawPassword));
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return this.target.matches(this.decoder.apply(rawPassword), encodedPassword);
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        return this.target.upgradeEncoding(encodedPassword);
    }
}
