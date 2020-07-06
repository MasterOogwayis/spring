package com.zsw.test;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;

/**
 * @author ZhangShaowei on 2020/6/1 14:16
 */
public class TestGZip {

    @SneakyThrows
    public static void main(String[] args) {
//        for (int i = 0; i < 100; i++) {
//            System.out.println(RandomStringUtils.randomAlphanumeric(16).toUpperCase());
//        }


        ClassPathResource classPathResource = new ClassPathResource("1.txt");

        System.err.println(IOUtils.toString(classPathResource.getInputStream()));


//        String data = "NO202006011417001234";
//        AESUtil aesUtil = new AESUtil();
//        String aesSecureKey = aesUtil.getAESSecureKey();
//        String encrypt = aesUtil.encrypt(data, aesSecureKey);
//        System.out.println("encrypt: " + encrypt);
//
//        byte[] decode = Base64.getDecoder().decode(encrypt);
//
//        System.out.println(bytes2Hex(decode));

    }

    private static final char[] HEXES = {
            '0', '1', '2', '3',
            '4', '5', '6', '7',
            '8', '9', 'a', 'b',
            'c', 'd', 'e', 'f'
    };

    public static String bytes2Hex(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }

        StringBuilder hex = new StringBuilder();

        for (byte b : bytes) {
            hex.append(HEXES[(b >> 4) & 0x0F]);
            hex.append(HEXES[b & 0x0F]);
        }

        return hex.toString();
    }


}
