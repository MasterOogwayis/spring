package com.zsw.encryption;

import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.SecureRandom;
import java.util.Objects;

/**
 * @author ZhangShaowei on 2018/10/15 18:48
 **/
public class TestUtils {


    public static void main(String[] args) {
        String key = "qlYsb8LOTuX7oBSsFcptIhO+/6qPp7WkGisFY+C1JoL3B642BDx8w+/gA6ualrvRq+nL3hTJZqcjmEcWXm6VAA==";
        String data = "{'repairPhone':'18547854787','customPhone':'12365478965','captchav':'58m7'}";
        String iv = "1234567800000000";
        String sncStr = "Ol0aOkm0I7npQE7LR10e1I/A1Yl/75V6VVNKHuyrt0sHCT6tm6PnFZMxX+huLhUHV32iqMCj9WxbcoZ8/d5UVh89Hk2tihANeY+b6ZikL1AoMirv9n4uSzPD1zJXqDWufD3mWHEy1LCcZ1cVxiY3HljrlIdf8Me6TgBAOCUughM=";
        byte[] enc = Base64Utils.decodeFromString(sncStr);

        System.out.println(new String(Objects.requireNonNull(decrypt(enc, key.substring(0, 16).getBytes(), iv.getBytes()))));



    }

    public static byte[] encrypt(String content, String key, String iv) {

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(content.getBytes()));
            SecretKey skey = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, skey, new IvParameterSpec(iv.getBytes()));
            byte[] result = cipher.doFinal(content.getBytes());
            return result;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("exception:" + e.toString());
        }
        return null;
    }

    public static byte[] decrypt(byte[] content, byte[] keyBytes, byte[] iv) {

        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom(keyBytes));//key长可设为128，192，256位，这里只能设为128
            SecretKey key = keyGenerator.generateKey();
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            System.out.println("exception:" + e.toString());
        }
        return null;
    }

}
