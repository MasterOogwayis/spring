package com.spring.security;

import org.junit.Test;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author ZhangShaowei on 2021/12/20 14:37
 */
public class PasswordEncoderTests {


    @Test
    public void test() {
        long timer = System.currentTimeMillis();
        Argon2PasswordEncoder passwordEncoder = new Argon2PasswordEncoder();
        String result = passwordEncoder.encode("123456");
        System.out.println(result);
        System.out.println("timer: " + (System.currentTimeMillis() - timer));
        timer = System.currentTimeMillis();

        System.err.println(passwordEncoder.matches("123456", result));
        System.out.println("timer: " + (System.currentTimeMillis() - timer));
    }


    @Test
    public void testBCrypt() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(30);
        long timer = System.currentTimeMillis();

        passwordEncoder.encode("123123123");

        System.out.println("timer: " + (System.currentTimeMillis() - timer));


    }

}
