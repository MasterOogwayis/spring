package com.zsw.base.utils;

import org.springframework.core.io.ClassPathResource;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.security.*;
import java.security.cert.Certificate;

/**
 * ExportCert
 *
 * @author ZhangShaowei on 2018/5/16 10:57
 **/
public class ExportCert {

    //导出证书 base64格式
    public static void exportCert(KeyStore keyStore, String alias, String exportFile) throws Exception {
        Certificate certificate = keyStore.getCertificate(alias);
        BASE64Encoder encoder = new BASE64Encoder();
        String encoded = encoder.encode(certificate.getEncoded());
        FileWriter fw = new FileWriter(exportFile);
        fw.write("------Begin Certificate----- \r\n ");//非必须
        fw.write(encoded);
        fw.write("\r\n-----End Certificate-----");//非必须
        fw.close();
    }

    //得到KeyPair
    public static KeyPair getKeyPair(KeyStore keyStore, String alias, char[] password){
        try{
            Key key = keyStore.getKey(alias, password);
            if (key instanceof PrivateKey){
                Certificate certificate = keyStore.getCertificate(alias);
                PublicKey publicKey = certificate.getPublicKey();
                return new KeyPair(publicKey, (PrivateKey) key);
            }
        }catch (UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException e){
            e.printStackTrace();
        }
        return null;
    }

    //导出私钥
    public static void exportPrivateKey(PrivateKey privateKey, String exportFile) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        String encoded = encoder.encode(privateKey.getEncoded());
        FileWriter fileWriter = new FileWriter(exportFile);
        fileWriter.write("-----BEGIN PRIVATE KEY-----\r\n");//非必须
        fileWriter.write(encoded);
        fileWriter.write("\r\n-----END PRIVATE KEY-----");//非必须
        fileWriter.close();
    }

    //导出公钥
    public static void exportPublicKey(PublicKey publicKey, String exportFile) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        String encoded = encoder.encode(publicKey.getEncoded());
        FileWriter fileWriter = new FileWriter(exportFile);
        fileWriter.write("-----BEGIN PUBLIC KEY-----\r\n");//非必须
        fileWriter.write(encoded);
        fileWriter.write("\r\n-----END PUBLIC KEY-----");//非必须
        fileWriter.close();
    }

    public static void main(String[] args) throws Exception{
        String keyStoreType = "jks";
        String keystoreFile = "/data/keystore/oauth2.jks";
        String password = "111111"; //keystore的解析密码
        String friendPassword = "111111";//条目的解析密码

        KeyStore keyStore = KeyStore.getInstance(keyStoreType);
        keyStore.load(new FileInputStream(keystoreFile), password.toCharArray());

        String alias = "oauth2";//条目别名
        String exportCertFile = "/data/keystore/cert.crt";
        String exportPrivateFile = "/data/keystore/privateKey.key";
        String exportPublicFile = "/data/keystore/publicKey.key";

        ExportCert.exportCert(keyStore, alias, exportCertFile);
        //注意这里的密码是你的别名对应的密码，不指定的话就是你的keystore的解析密码
        KeyPair keyPair = ExportCert.getKeyPair(keyStore, alias, friendPassword.toCharArray());
        ExportCert.exportPrivateKey(keyPair.getPrivate(), exportPrivateFile);
        ExportCert.exportPublicKey(keyPair.getPublic(), exportPublicFile);

        System.out.println("OK");

    }

}
