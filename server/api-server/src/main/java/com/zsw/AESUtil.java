package com.zsw;

import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES
 */
public class AESUtil {

    public static final String DEFAULT_KEY = "l4cdpko1gxdawgg9ziqf6yzr6shsndhh";

    private static final String KEY_AES = "AES/ECB/PKCS5Padding";

    /**
     * encrypt
     *
     * @param data
     * @param aesKey
     * @return
     * @throws Exception
     */
    @SneakyThrows
    public static String encrypt(String data, String aesKey){
        Cipher cipher = Cipher.getInstance(KEY_AES);
        int blockSize = cipher.getBlockSize();

        byte[] dataBytes = data.getBytes();
        int plaintextLength = dataBytes.length;
        if (plaintextLength % blockSize != 0) {
            plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
        }

        byte[] plaintext = new byte[plaintextLength];
        System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

        SecretKeySpec keyspec = new SecretKeySpec(aesKey.getBytes(), "AES");

        cipher.init(Cipher.ENCRYPT_MODE, keyspec);
        byte[] encrypted = cipher.doFinal(plaintext);

        return Base64.encodeBase64URLSafeString(encrypted);
    }

    /**
     * decrypt
     *
     * @param data
     * @param aesKey
     * @return
     * @throws Exception
     */
    @SneakyThrows
    public static String decrypt(String data, String aesKey){
        byte[] encrypted1 = Base64.decodeBase64(data);

        Cipher cipher = Cipher.getInstance(KEY_AES);
        SecretKeySpec keyspec = new SecretKeySpec(aesKey.getBytes(), "AES");

        cipher.init(Cipher.DECRYPT_MODE, keyspec);

        byte[] original = cipher.doFinal(encrypted1);
        return StringUtils.newStringUtf8(original);
    }

}
