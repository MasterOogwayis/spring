package com.zsw.test;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import static com.zsw.test.StringUtils.bytesToHexString;


/**
 * @author ZhangShaowei on 2020/5/20 14:24
 */
@Slf4j
@AllArgsConstructor
public class RsaSignature {

    /**
     * 目前仅支持 SHA256withRSA
     */
    private static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    private static final String ENCODING = "UTF-8";

    /**
     *
     */
    private RSAPublicKey publicKey;

    /**
     *
     */
    private RSAPrivateKey privateKey;

    public byte[] sign(String plainText) {
        byte[] signed = null;
        try {
            Signature Sign = Signature.getInstance(SIGNATURE_ALGORITHM);
            Sign.initSign(privateKey);
            Sign.update(plainText.getBytes());
            signed = Sign.sign();
            log.debug("SHA256withRSA签名后-----》{}", bytesToHexString(signed));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return signed;
    }


    public boolean verify(String plainText, byte[] signed) {
        try {
            Signature verifySign = Signature.getInstance(SIGNATURE_ALGORITHM);
            verifySign.initVerify(publicKey);
            verifySign.update(plainText.getBytes());
            return verifySign.verify(signed);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return false;
    }


}
