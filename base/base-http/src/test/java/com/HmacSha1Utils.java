package com;

import lombok.SneakyThrows;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Base64Utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author ZhangShaowei on 2019/5/27 17:02
 **/
public class HmacSha1Utils {

    private static final String HMAC_SHA1_ALGORITHM = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

    @SneakyThrows
    public static String genHMAC(String data, String key) {
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKeySpec signinKey = new SecretKeySpec(key.getBytes(ENCODING), HMAC_SHA1_ALGORITHM);
        //生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(HMAC_SHA1_ALGORITHM);
        //用给定密钥初始化 Mac 对象
        mac.init(signinKey);
        //完成 Mac 操作
        byte[] rawHmac = mac.doFinal(data.getBytes());
        return Base64Utils.encodeToString(rawHmac);
    }

}
