package com;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * @author ZhangShaowei on 2019/4/3 13:53
 **/
public class StaticTests {

    public static void main(String[] args) throws UnsupportedEncodingException {

        byte[] encode = Base64.getEncoder().encode("root:root".getBytes("UTF-8"));

        System.out.println(new String(encode, "UTF-8"));

    }


}
