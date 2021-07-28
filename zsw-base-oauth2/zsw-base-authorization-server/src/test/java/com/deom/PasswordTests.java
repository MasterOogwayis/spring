package com.deom;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ZhangShaowei on 2021/7/26 18:16
 */
public class PasswordTests {

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        System.out.println(passwordEncoder.encode("bbx-backend"));
        System.err.println(passwordEncoder.encode(System.currentTimeMillis() + ""));
    }

}
