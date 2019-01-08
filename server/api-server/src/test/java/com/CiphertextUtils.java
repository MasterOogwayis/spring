package com;

import org.springframework.util.Base64Utils;

import java.io.UnsupportedEncodingException;

/**
 * 加密解密工具类
 *
 * @author lijianjun 2017年1月12日
 */
public class CiphertextUtils {

    private static final String CHARSET = "utf-8";

    /**
     * 加密
     *
     * @return
     * @author lijianjun 2017年1月12日
     * @version 1.0.0
     * @since 1.7.x
     */
    public static String encryption(String content) {
        if (content == null) {
            return null;
        }
        try {
            return Base64Utils.encodeToString(content.getBytes(CHARSET)).replaceAll("[\\s*\t\n\r]", "");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密
     *
     * @param ciphertext
     * @return
     * @author lijianjun 2017年1月12日
     * @version 1.0.0
     * @since 1.7.x
     */
    public static String decryption(String ciphertext) {
        if (ciphertext == null) {
            return null;
        }
        return new String(Base64Utils.decodeFromString(ciphertext));
    }

    public static void main(String[] args) {
        System.out.println(decryption("MTAwMDAwMDAwMDM="));
        System.out.println(encryption("13541120934"));
    }
}
