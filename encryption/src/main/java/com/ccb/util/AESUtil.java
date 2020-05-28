package com.ccb.util;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class AESUtil {
    private static final String ENCODING_CODE = "UTF-8";
    private static final String KEY_ALGORITHM = "AES";
    /**
     * 密钥长度
     */
    private static final int KEY_ALGORITHM_SIZE = 128;
    /**
     * 安全序列模式
     */
    private static final String SECURE_RANDOM = "SHA1PRNG";
    /**
     * 算法/工作模式/填充方式
     */
    private static final String CIPHER_ALGORITHM_ECB = "AES/ECB/PKCS5Padding";

    public static void main(String[] args) {
        AESUtil aesUtils = new AESUtil();
//        String count = "Vno=2&Sgn_Algr=SHA256withRSA&CstPty_Ordr_No=123456789&Fee_Itm_Prj_User_No=12345&MrchCd=105000086510004&PyF_ClCd=1001&Admn_Rgon_Cd=123456&Fee_Itm_PyF_MtdCd=00&Usr_ID=1234567890&Crdt_Tp=1010&Crdt_No=110105199012272168&MblPh_No=12345678901&Email=abc@sina.com&Ret_Url=userUrl&Tms=20180101101223&IsMobile=0&SIGN_INF=307387394851D9EF8C9348493EDE3332930AE4102331307387394851D9EF8C9348493EDE3332930AE4102331307387394851D9EF8C9348493EDE3332930AE410233121A8CB82B1";
//        String password = aesUtils.getAESSecureKey();
//        String encryptStr = aesUtils.encrypt(count, password);
//        System.out.println("加密后的结果：" + encryptStr);
//
//        String decryptStr = aesUtils.decrypt(encryptStr, password);
//        System.out.println("解密后的结果：" + decryptStr);

        //生成AES密钥
        aesUtils.getAESSecureKey();

    }

    /**
     * 根据用户密钥进行加密
     *
     * @param count    需要加密的字符串
     * @param password 加密的密钥
     * @return
     */
    public String encrypt(String count, String password) {
        try {
            //加密
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
            cipher.init(Cipher.ENCRYPT_MODE, getKey(password));
            byte[] result = cipher.doFinal(count.getBytes(ENCODING_CODE));
            return Base64.getEncoder().encodeToString(result);//通过Base64转码返回
        } catch (Exception e) {
            System.out.println("AES加密失败！");
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 解密AES加密过的字符串
     *
     * @param count    加密过的字符串
     * @param password 加密时的密钥
     * @return
     */
    public String decrypt(String count, String password) {
        try {
            //解密 创建密码器
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM_ECB);
            cipher.init(Cipher.DECRYPT_MODE, getKey(password));
            byte[] result = cipher.doFinal(Base64.getDecoder().decode(count));
            return new String(result);
        } catch (Exception e) {
            System.out.println("AES解密失败！");
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 用于生成AES密钥的工具
     */
    public String getAESSecureKey() {
        String aesKey = "";
        try {//生成KEY, AES要求密钥长度为 256
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
            keyGenerator.init(KEY_ALGORITHM_SIZE);
            //生成密钥
            SecretKey secretKey = keyGenerator.generateKey();
            aesKey = Hex.encodeHexString(secretKey.getEncoded());
            System.out.println("生成AES密钥：" + Hex.encodeHexString(secretKey.getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return aesKey;
    }


    /**
     * 创建AES KEY生产者
     *
     * @param password
     * @return
     */
    public SecretKey getKey(String password) {
        try {
            //生成KEY,AES 要求密钥长度为 256
            KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom secureRandom = SecureRandom.getInstance(SECURE_RANDOM);
            secureRandom.setSeed(password.getBytes());
            keyGenerator.init(KEY_ALGORITHM_SIZE, secureRandom);
            //根据用户密码生成密钥
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] byteKey = secretKey.getEncoded();
            //转换KEY AES专属密钥
            return new SecretKeySpec(byteKey, KEY_ALGORITHM);
        } catch (Exception e) {
            System.out.println("创建AES KEY失败！");
            e.printStackTrace();
            return null;
        }
    }
}
