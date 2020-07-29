package com.zsw.encryption;

import javax.crypto.Cipher;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAUtil {

    public static final String PUBLIC_KEY = "RSAPublicKey";
    public static final String PRIVATE_KEY = "RSAPrivateKey";
    public static final String KEY_ALGORITHM = "RSA";

    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    private static final String ENCODING = "UTF-8";

    /**
     * 512-65536 & 64的倍数
     */
    public static final int KEY_SIZE = 1024 * 2;

    /**
     * 生成RSA的公钥和私钥
     */
    public static Map<String, Object> initKey() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGenerator.initialize(KEY_SIZE);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<>();
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 获得公钥
     */
    public static RSAPublicKey getpublicKey(Map<String, Object> keyMap) {
        RSAPublicKey publicKey = (RSAPublicKey) keyMap.get(PUBLIC_KEY);
        return publicKey;
    }

    /**
     * 获得私钥
     */
    public static RSAPrivateKey getPrivateKey(Map<String, Object> keyMap) {
        RSAPrivateKey privateKey = (RSAPrivateKey) keyMap.get(PRIVATE_KEY);
        return privateKey;
    }


    /**
     * 还原公钥
     *
     * @param keyBytes
     * @return
     */
    public static PublicKey restorePublicKey(byte[] keyBytes) {
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            return factory.generatePublic(x509EncodedKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 还原私钥
     *
     * @param keyBytes
     * @return
     */
    public static PrivateKey restorePrivateKey(byte[] keyBytes) {
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        try {
            KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
            return factory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return null;
    }








    /**
     * 公钥加密
     */
    public static byte[] encrypt(byte[] data, RSAPublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    /**
     * 私钥解密
     */
    public static byte[] decrypt(byte[] data, RSAPrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }


}
