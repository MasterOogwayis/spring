package com.zsw;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.MessageDigestPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author ZhangShaowei on 2020/1/3 14:26
 */
@Slf4j
public class SecurityTests {

    @Test
    public void testEncrypt() {
        PasswordEncoder delegatingPasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        PasswordEncoder passwordEncoder = new MessageDigestPasswordEncoder("MD5");

        String password = "123456";

        String newEncode = delegatingPasswordEncoder.encode(password);


        String md5Encode = passwordEncoder.encode(password);

        log.debug("new encode: {}", newEncode);
        log.debug("md5 encode: {}", md5Encode);


        String encrypt = "{MD5}2a6d26af012a0b076c21a4ba2e55ac04";
        System.out.println(delegatingPasswordEncoder.matches(password, encrypt));

        System.out.println(delegatingPasswordEncoder.matches(password, md5Encode));




    }


}
