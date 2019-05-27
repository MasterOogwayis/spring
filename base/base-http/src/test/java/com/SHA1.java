package com;

import org.springframework.util.Base64Utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author ZhangShaowei on 2018/11/20 16:44
 **/
public class SHA1 {

    private static final String HMAC_SHA1 = "HmacSHA1";

    private static final String CHARSET = "utf-8";


    public static byte[] hmacSHA1(String body, String key) {
        try {
            SecretKeySpec keySpec = new SecretKeySpec(key.getBytes(CHARSET), HMAC_SHA1);
            Mac mac = Mac.getInstance(HMAC_SHA1);
            mac.init(keySpec);
            return mac.doFinal(body.getBytes(CHARSET));
        } catch (Exception e) {
            return null;
        }
    }

    public static String hmacSHA1ToBase64String(String body, String key) {
        return Base64Utils.encodeToString(hmacSHA1(body, key));
    }


}
