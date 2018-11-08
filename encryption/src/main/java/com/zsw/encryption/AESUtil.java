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

    private static final String KEY_AES = "AES/ECB/PKCS5Padding";

    public static String encrypt(String data, String aesKey) throws Exception {
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
