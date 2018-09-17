package com;

import com.zsw.base.utils.DateUtils;
import org.springframework.security.crypto.codec.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.Security;


/**
 * 描述：手机端加密算法
 *
 * @author 2015-1-31
 * @version 1.1.0
 * @since 1.1.0
 */
@SuppressWarnings("restriction")
public class DesBase64For3 {
    static {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
    }

    private static final String MCRYPT_TRIPLEDES = "DESede";
    private static final String TRANSFORMATION = "DESede/CBC/PKCS5Padding";
    private final static String ENCODING = "utf-8";
    public static final String KEY = "refcencryptkeyrefcencryptkey";
    public static final String IV = "83452617";

    public static String decrypt(String encryptStr) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(KEY.getBytes(ENCODING));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(MCRYPT_TRIPLEDES);
        SecretKey sec = keyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        IvParameterSpec ivParameters = new IvParameterSpec(IV.getBytes(ENCODING));
        cipher.init(Cipher.DECRYPT_MODE, sec, ivParameters);
        return new String(cipher.doFinal(Base64.decode(encryptStr.getBytes())), ENCODING);
    }

    public static String encrypt(String plainText) throws Exception {
        DESedeKeySpec spec = new DESedeKeySpec(KEY.getBytes(ENCODING));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey sec = keyFactory.generateSecret(spec);
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        IvParameterSpec ivParameters = new IvParameterSpec(IV.getBytes(ENCODING));
        cipher.init(Cipher.ENCRYPT_MODE, sec, ivParameters);
        return new String(Base64.encode(cipher.doFinal(plainText.getBytes(ENCODING))), ENCODING);
    }

    public static byte[] generateSecretKey() throws NoSuchAlgorithmException {
        KeyGenerator keygen = KeyGenerator.getInstance(MCRYPT_TRIPLEDES);
        return keygen.generateKey().getEncoded();
    }


    public static void main(String args[]) throws Exception {

        String openid = "oKpBTtzWAWUZeKIPm9_mILVITK8A";
        String token = encrypt(openid + ":" + DateUtils.timeLabel());
        System.out.println(token);
        String[] arr = decrypt(token).split(":");
        System.err.println(arr[0]);
        System.err.println(arr[1]);

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