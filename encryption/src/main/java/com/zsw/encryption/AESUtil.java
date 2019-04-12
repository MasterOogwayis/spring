package com.zsw.encryption;

import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * AES算法编程实现
 *
 */
public class AESUtil {

    public static final String DEFAULT_KEY = "l4cdpko1gxdawgg9ziqf6yzr6shsndhh";

    private static final String KEY_AES = "AES/ECB/PKCS5Padding";

    public static void main(String[] args) throws Exception {
        String data = "{\"name\":\"zsw\"}";
        System.out.println("length: " + data.length());
        String encrypt = encrypt(data, DEFAULT_KEY);
        System.out.println(encrypt);
        String c = decrypt(encrypt, DEFAULT_KEY);
        System.err.println(c.equals(data));
    }

    public static String encrypt(String data, String aesKey) throws Exception {
        Cipher cipher = Cipher.getInstance(KEY_AES);
        int blockSize = cipher.getBlockSize();

        byte[] dataBytes = data.getBytes();
//        int plaintextLength = dataBytes.length;
//        if (plaintextLength % blockSize != 0) {
//            plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
//        }
//
//        byte[] plaintext = new byte[plaintextLength];
//        System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

        SecretKeySpec keyspec = new SecretKeySpec(aesKey.getBytes(), "AES");

        cipher.init(Cipher.ENCRYPT_MODE, keyspec);
        byte[] encrypted = cipher.doFinal(dataBytes);

        return Base64Utils.encodeToString(encrypted);
    }

    public static String decrypt(String data, String aesKey) throws Exception {
        byte[] encrypted1 = new BASE64Decoder().decodeBuffer(data);

        Cipher cipher = Cipher.getInstance(KEY_AES);
        SecretKeySpec keyspec = new SecretKeySpec(aesKey.getBytes(), "AES");

        cipher.init(Cipher.DECRYPT_MODE, keyspec);

        byte[] original = cipher.doFinal(encrypted1);
        return new String(original);
    }

}
