package com.zsw.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zsw.encryption.AESUtil;
import com.zsw.encryption.RSAUtil;
import lombok.SneakyThrows;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

import static com.zsw.test.StringUtils.bytesToHexString;

/**
 * @author ZhangShaowei on 2018/10/15 17:50
 **/
public class TestAES {

    private static final Gson gson = new GsonBuilder().create();

    public static void main(String[] args) throws Exception {
        Map<String, Object> map = RSAUtil.initKey();
        RSAPrivateKey privateKey = RSAUtil.getPrivateKey(map);
        RSAPublicKey publicKey = RSAUtil.getpublicKey(map);

        RsaSignature signature = new RsaSignature(publicKey, privateKey);

        String plainText = "asdasdasdasdasdasdasdasdasdasdasdasd";

        byte[] sign = signature.sign(plainText);
        System.out.println("sign: " + bytesToHexString(sign));

        boolean verify = signature.verify(plainText, sign);
        System.err.println(verify);


    }


    @SneakyThrows
    public static void test1() {
        String token = "qlYsb8LOTuX7oBSsFcptIhO+/6qPp7WkGisFY+C1JoL3B642BDx8w+/gA6ualrvRq+nL3hTJZqcjmEcWXm6VAA==";
        String data = "{'repairPhone':'18547854787','customPhone':'12365478965','captchav':'58m7'}";
//        System.out.println("加密密钥和解密密钥：" + key.substring(0, 32));
//        String encrypt = AESUtil.encrypt(data, key.substring(0, 32));
//        System.out.println("加密后：" + encrypt);
//        String decrypt = AESUtil.decrypt(encrypt, key.substring(0, 32));
//        System.out.println("解密后：" + decrypt);

        String aesKey = token.substring(0, 32);
        System.out.println("aesKey: " + aesKey);

        String dataStr = "{\"maxResult\":10,\"id\":-1,\"token\":\"qlYsb8LOTuX7oBSsFcptIhO+/6qPp7WkGisFY+C1JoL3B642BDx8w+/gA6ualrvRq+nL3hTJZqcjmEcWXm6VAA==\"}";
        System.out.println("dataStr: " + dataStr);

        OpenIdDto dto1 = gson.fromJson(dataStr, OpenIdDto.class);


        String encStr = AESUtil.encrypt(dataStr, aesKey);

        System.out.println("encStr: " + encStr);

        String decStr = AESUtil.decrypt(encStr, "1234567812345678qlYsb8LOTuX7oBSsFcptIhO+/6qPp7Wk123456".substring(16, 48));

        System.out.println("decStr: " + decStr);

        System.out.println("equals: " + dataStr.equals(decStr));

        OpenIdDto dto = gson.fromJson(decStr.trim(), OpenIdDto.class);

        System.out.println(dto.getToken());
    }

}
