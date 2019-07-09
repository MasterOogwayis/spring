package com;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author ZhangShaowei on 2019/6/27 17:59
 **/
public class STest {
    public static void main(String[] args) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("asd"));

        String str = "asd";
        String a = str.replace("a", "");
        String replace = str.replace("a".charAt(0), "b".charAt(0));


    }

}
