package com.zsw.test;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.zsw.encryption.RSAUtil;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.List;
import java.util.stream.Collectors;

public class TestRSA {

    //待加密原文
    public static final String DATA = "hi, welcome to my git area!";

    public static void main(String[] args) throws Exception {

        String publicKey = readKey("/data/keystore/publicKey.key");
        String privateKey = readKey("/data/keystore/privateKey.key");


//        Map<String, Object> keyMap = RSAUtil.initKey();

        RSAPublicKey rsaPublicKey = loadPublicKeyByStr(publicKey);
        RSAPrivateKey rsaPrivateKey = loadPrivateKeyByStr(privateKey);


//        RSAPublicKey rsaPublicKey = RSAUtil.getpublicKey(keyMap);
//        RSAPrivateKey rsaPrivateKey = RSAUtil.getPrivateKey(keyMap);
        System.out.println("RSA PublicKey: " + rsaPublicKey);
        System.out.println("RSA PrivateKey: " + rsaPrivateKey);

        byte[] rsaResult = RSAUtil.encrypt(DATA.getBytes(), rsaPublicKey);
        String encrypt = new BASE64Encoder().encode(rsaResult);
        System.err.println("RSA 加密: " + encrypt);

        byte[] plainResult = RSAUtil.decrypt(new BASE64Decoder().decodeBuffer(encrypt), rsaPrivateKey);
        System.err.println("RSA 解密: " + new String(plainResult, "utf-8"));
    }


    /**
     * 从文件中输入流中加载公钥
     *
     * @param path
     * @return
     * @throws Exception 加载公钥时产生的异常
     */
    public static String readFile(String path) throws Exception {
        return Files.readAllLines(Paths.get(path)).stream().collect(Collectors.joining());
    }

    public static String readKey(String filePath) {
        try {
            List<String> data = Files.readAllLines(Paths.get(filePath));
            data.remove(data.size() - 1);
            data.remove(0);
            return data.stream().collect(Collectors.joining());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从字符串中加载公钥
     *
     * @param publicKeyStr 公钥数据字符串
     * @throws Exception 加载公钥时产生的异常
     */
    public static RSAPublicKey loadPublicKeyByStr(String publicKeyStr)
            throws Exception {
        byte[] buffer = Base64.decode(publicKeyStr);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }

    public static RSAPrivateKey loadPrivateKeyByStr(String privateKeyStr)
            throws Exception {
        byte[] buffer = Base64.decode(privateKeyStr);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPrivateKey) keyFactory.generatePrivate(keySpec);
    }

}
