package com;

import java.security.Security;

/**
 * 描述：des加密算法
 *
 * @author 2015-1-31
 * @version 1.1.0
 * @since 1.1.0
 */
@SuppressWarnings("restriction")
public final class DesBase64 extends DesBase64For3 {
    /**
     * 工具类
     */
    private DesBase64() {
    }

    static {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
    }

    /**  */
    private static final String DES_VMC_KEY = "ljypzswlljypzswlljypzswl";
    /**  */
    private static final String IV = "12345678";

    /**
     * 加密
     *
     * @param encryptStr 已加密String
     * @return String
     * @throws Exception cert
     */
    public static String decrypt(final String encryptStr) throws Exception {
        return decrypt(encryptStr, DES_VMC_KEY, IV);
    }

    /**
     * 解密
     *
     * @param plainText 已加密String
     * @return String
     * @throws Exception cert
     */
    public static String encrypt(final String plainText) throws Exception {
        return encrypt(plainText, DES_VMC_KEY, IV);
    }

}