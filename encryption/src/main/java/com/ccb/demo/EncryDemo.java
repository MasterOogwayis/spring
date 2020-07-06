package com.ccb.demo;

import com.alibaba.fastjson.JSONObject;
import com.ccb.util.AESUtil;
import com.ccb.util.HttpUtil;
import com.ccb.util.RSAUtilV2;
import com.ccb.util.SHA256withRSA;

/**
 * 用户请求政融支付加密参数Demo
 */
public class EncryDemo {
    /**
     * 双方用于加解密的密钥 可调用 AESUtil.getAESSecureKey() 获取
     */
    private static final String AES_KEY = "bf7f4a47755bfd811b36c7b6dba88c34";
    /**
     * 签名私钥 使用调用政融原有私钥 否则会导致验签不过。
     */
    private static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDcj2lNGdpEwFY196A/pcT9s9/+XgfI6rYb5GeBoOattnEL7JAe3Zbob0XtZDOnuYP+3yaorqVmDvwFAsACt9ac8jXXpghvLHIW8rdv25jbjlajOcwQy/H42+tBKgZV7oO3r8zj58PvjXjj7wwFEHiacRMI3Y6YCYi60omKQeCk9bY7L5FK7Mym4GrNT4+tv5A/zyBS7NuaCO8reup5BVtg2MjVuizoMDOPNr7sT1oqFj21KqbIrL+TzQe/hiddOrRVY8llAtXmq0lvMcjfnfgCtbs+rhYijIXjfi1VEh+cq9Gl4RY/3Vh5KE58jgVk7ggFuyvDmr57sDtb62V8RgZAgMBAAECggEAKTNfuy6n0zmPzIctSQWD2VAdTiGl0yzc2iZuYDfufEogC+xfVPLd8G7L1Gim0PzhY9USA25KSaWD9AZ0RDkkNTfhlhAiY0DAAOs0tTjRovzjoUxVEr2cdm0FjhfNkNP8j7il+cVkuAyI26Er/W38ELnSO4NKBZYpnfxeK4PkxLqZW8/NKUXJni7FaPni2S7tWhgkt7Zqa/gdLnCQMLaf9xePhLzqrM2gT10kkZjBDQ4WqvfsgLRMUJ8B40e3QezYxTtYlzaZCSibBMI+gMnBKJOzXf01wUybNpBWYmDFMDoMColILB2GeSTzO92V0DceewaOamBpKaXTj/DeMFZuuQKBgQDbiHF+/GOIirzdWe0SiJkKmv4MRvIcTGrwGOepdEimbDvFaq+M4JxS0n5drufxq1E8kmDAcLfatvf7NZ0+FZBMMMfl3giLcpZvgCxKxABefdZdkOsTvTgG0ckKwf9qO2Yq+A6tlk52Hv80tecXsIDGdvQnU/52xryTUBcbdWjfCwKBgQCZR/FaPUFDjkRwU/uHq21azGUNCUjKH6O1ULRBWJdQBv5OLvCxqvwXSJGAYaG1JdwP15jI4jNzJfmmM8IT3Jw65zAlhXe01l2LEf7PohAVN5LDqd5Lq2rwCYhAV5aspk7Wc2gHhTsyRgTZWeLHF7i1Xc0JlmW5mgW4/t7QExCr6wKBgCs+z4zCTyEgo1+/TTIvcmZibdUhTKRCcXZmkYwR+hW+kG+tOnO381NlX7s4rzwuEUyrUR/XlIAjNupnf1gxi0FXAqnHeUtvAS9pwk/gGGqEw2ufFo/G4HiHbuENojDdDp08TDfpuf8O0BskEifafyOZXzM4GpJvR8qFJmgkUspNAoGAaWGl1FWixhBMizGiD59TOoalvrWwXo4sHh8THo4K0ZFNS3FIN84HLPbOWgZFh+Y0iou+VfX2S2dDYPnap48XtgN1/YXqS+DJRTClEBkql3uyomTqGPoMNmVHUH0ncSGRuCx1zB3UGfc7pDcBC8IKUl7f9YR6AYWcA5julP1Wi+sCgYEAyysEFyNAlTaDrtVMqYcRZ2Gufqoi/2qUzFFQnT6HTuhiB+S67p0VYaTPI4/XllrftkfiVYmt/gI2YuGhzrgO3vF6ucsqrNfnFdeA2HUomcO4XkFygt/die1pgAZ+XRL232F1kIWSbmIoerVBubc+vAZpEtVjVVJNgMMPSz29Nrs=";

    //请求地址
    private static final String REQUEST_URL = "http://govpaytestay.mytunnel.site/online/bmjf/MainPlat";
    /**
     * 使用政融支付平台公钥对加密密钥进行RSA非对称加密 此处密钥更换为具体公钥
     */
    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkxJVDKgDhDGS/OCBDmqD1F5j8yzeimmEmaFjz3w+oO6vUDpJ+bGesONsK0Ue1ttOyFBm0x1IRRmJJWTTL9NSNm+f8P1nttZiRSdWIgOjXwxZvasndDN9cUArilIGPNhpLxkHaZkzvym/GKPPbr/p5ys/iFFjZsxGTJ5k84KnK83yC503TGqLweOp24/ghJZO80lPH3ZNTQUqmV4JodTdRvirJbPAZBoc2lUkizYX4NitAqCDnXXFN8JT9C2tJONy6s8JRsuXC7Y6+kffXTxUne2UAvuwkyBJtMsiH3a38yj9V9PLbggOST9gpUG7ISwe2PGWamYsx0/tJJR8avUnwwIDAQAB";

    public static void main(String[] args) {
        EncryDemo demo = new EncryDemo();
        String param = "Vno=2&Sgn_Algr=SHA256withRSA&Usr_ID=12345&Crdt_Nm=张三&Crdt_Tp=1010&Crdt_No=130132199809896781&MblPh_No=19801207665&IsMobile=2&Email=abc@sina.com&Tms=20190809121212";
        //demo.oldRequestZrzfDncryptParam(param);
        demo.newRequestZrzfDncryptParam(param);
        /**
         * 执行后续逻辑
         */
    }

    /**
     * 此场景模拟用户请求政融支付  适配未优化Form表单提交的用户
     * 获取到加密的Uri后跳转页面-进行缴费
     *
     * @param oriParam 签名原串 按实际接口原串拼接 这里以安阳为例
     * @return
     */
    public String oldRequestZrzfDncryptParam(String oriParam) {
        AESUtil aesUtil = new AESUtil();
        StringBuffer strbuff = new StringBuffer();

        //用户签名 -> 私钥签名 -> 用于双方认证
        String signInf = SHA256withRSA.sign(PRIVATE_KEY, oriParam);
        //拼接签名信息 用于加密
        String str = oriParam + "&SIGN_INF=" + signInf;
        System.out.println("参数信息用于加密:" + str);
        System.out.println("AES加密参数信息--------");
        String encryptParam = aesUtil.encrypt(str, AES_KEY);
        System.out.println("AES加密后参数:" + encryptParam);

        //加密AES密钥 这里使用RSA加密 政融会解密获取AES密钥后再解密请求参数
        String rsaEncryKey = RSAUtilV2.encryptByPublic(AES_KEY.getBytes(), RSAUtilV2.getPublicKey(PUBLIC_KEY));
        System.out.println("RSA加密AES密钥：" + rsaEncryKey);
        /**
         * 拼接请求政融最终的uri 例如：
         * https://ay.govpay.ccb.com/online/bmjf/MainPlat?Encrypt_Ind=1&Encrypt_key=xxx&Bus_para=XXX
         */
        strbuff.append(REQUEST_URL);
        //1表示加密 0表示不加密 这里默认拼1
        strbuff.append("?Encrypt_Ind=1");
        //拼接AES加密后的密钥
        strbuff.append("&Encrypt_key=");
        strbuff.append(rsaEncryKey);
        //拼接AES加密后参数
        strbuff.append("&Bus_para=");
        strbuff.append(encryptParam);
        System.out.println("用户请求政融支付加密后URI:" + strbuff.toString());
        return strbuff.toString();
    }

    /**
     * 此场景模拟用户请求政融支付 适配已优化Form表单Post请求的用户 这里使用HTTP模拟
     * 获取到加密的参数后请求政融平台
     *
     * @param oriParam 签名原串 按实际接口原串拼接 这里以安阳为例
     * @return
     */
    public String newRequestZrzfDncryptParam(String oriParam) {
        AESUtil aesUtil = new AESUtil();
        //用户签名 -> 私钥签名 -> 用于双方认证
        String signInf = SHA256withRSA.sign(PRIVATE_KEY, oriParam);
        //拼接签名信息 用于加密
        String str = oriParam + "&SIGN_INF=" + signInf;
        System.out.println("参数信息用于加密:" + str);
        System.out.println("AES加密参数信息--------");
        String encryptParam = aesUtil.encrypt(str, AES_KEY);
        System.out.println("AES加密后参数:" + encryptParam);

        //加密AES密钥 这里使用RSA加密 政融会解密获取AES密钥后再解密请求参数
        String rsaEncryKey = RSAUtilV2.encryptByPublic(AES_KEY.getBytes(), RSAUtilV2.getPublicKey(PUBLIC_KEY));
        System.out.println("RSA加密AES密钥：" + rsaEncryKey);
        /**
         * 组装请求政融的参数 实际按照接口文档为准！！！
         */
        JSONObject json = new JSONObject();
        //1表示加密 0表示不加密 这里默认拼1
        json.put("Encrypt_Ind", "1");
        //RSA加密后的AES密钥
        json.put("Encrypt_key", rsaEncryKey);
        //AES加密后参数
        json.put("Bus_para", encryptParam);
        System.out.println("请求参数:" + json.toJSONString());
        /**
         * 此处向政融发送请求。这里的url是安阳为例
         */
        //用户接收请求地址
        String result = "";
        try {
            result = HttpUtil.doJsonPost(REQUEST_URL, json.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("http返回信息:" + result);
        return result;
    }
}
