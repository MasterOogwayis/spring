package com;

import com.sun.org.apache.xml.internal.security.utils.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * asd
 *
 * @author ZhangShaowei on 2018/1/18 18:21
 **/
public class DesBase64For3 {

    static {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
    }

    private static final String MCRYPT_TRIPLEDES = "DESede";
    private static final String TRANSFORMATION = "DESede/CBC/PKCS5Padding";
    private final static String encoding = "utf-8";

    public static String decrypt(String encryptStr, String key, String iv) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(key.getBytes(encoding));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MCRYPT_TRIPLEDES);
        SecretKey sec = keyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        IvParameterSpec IvParameters = new IvParameterSpec(iv.getBytes(encoding));
        cipher.init(Cipher.DECRYPT_MODE, sec, IvParameters);
        return new String(cipher.doFinal(Base64.decode(encryptStr)),encoding);
    }

    public static String encrypt(String plainText, String key, String iv) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(key.getBytes(encoding));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey sec = keyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        IvParameterSpec IvParameters = new IvParameterSpec(iv.getBytes(encoding));
        cipher.init(Cipher.ENCRYPT_MODE, sec, IvParameters);
        return Base64.encode(cipher.doFinal(plainText.getBytes(encoding)));
    }

    public static byte[] generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance(MCRYPT_TRIPLEDES);
        return keygen.generateKey().getEncoded();
    }


    public static void main(String args[]) throws Exception {
//        String plainText = "{\"uPass\":\"111111\",\"uAccount\":\"15884427076\"}";
//        final byte[] secretBytes = TripleDES.generateSecretKey();
//        final byte[] secretBytes = "ljypzswlljypzswlljypzswl".getBytes();

//        final byte[] ivbytes = TripleDES.randomIVBytes();
//        final byte[] ivbytes = "12345678".getBytes();
//        System.out.println("plain text: " + plainText);
//        byte[] encrypt = DesFor3.encrypt(plainText.getBytes(), secretBytes, ivbytes);
//        System.out.println("cipher text 16进制: " + ConvertUtil.bytesToHexString(encrypt));
//        System.out.println("cipher text base64: " + Base64.encode(encrypt));
//        System.out.println("decrypt text: " + new String(DesFor3.decrypt(encrypt, secretBytes, ivbytes), "UTF-8"));
    }

}
