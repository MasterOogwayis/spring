package com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.Base64Utils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;


/**
 * test
 *
 * @author ZhangShaowei on 2018/4/3 10:05
 **/
public class StaticTests {

    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    private static final String RANDOM_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final java.util.Random RANDOM = new java.util.Random();

    public static String getRandomStr() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append(RANDOM_STR.charAt(RANDOM.nextInt(RANDOM_STR.length())));
        }
        return sb.toString();
    }

    public static void main(String[] args) throws Exception {

        String str = "eyJ0eXBlIjowLCJtYXhSZXN1bHQiOjEwLCJpZCI6LTEsInRva2VuIjoiUVNOR1NrNjlyYmRMc1Z4YklxditMNmpvTDBIYnFuNHFmSlFldGVhak41TitxSmJIRS9jK2lRPT0ifQ=";
        String data = "{\"type\":0,\"maxResult\":10,\"id\":-1,\"token\":\"QSNGSk69rbdLsVxbIqv+L6joL0Hbqn4qfJQeteajN5N+qJbHE/c+iQ==\"}";


//        System.err.println("spring Base64: " + new String(org.springframework.security.crypto.codec.Base64.encode(data.getBytes())));
        System.out.println("apache Base64: " + Base64.encodeBase64String(data.getBytes()));

        System.err.println(new String(Base64Utils.decode(Base64.encodeBase64String(data.getBytes()).getBytes())));

//        System.out.println(StringUtils.newStringUtf8(org.springframework.security.crypto.codec.Base64.encode(data.getBytes())));
//        System.out.println(Base64.encodeBase64String(data.getBytes()));
//        System.out.println(new String(Base64.decodeBase64(str)));
//        System.err.println(new String(Base64.decodeBase64(Base64.encodeBase64String(data.getBytes()))));


//        String encode = new BASE64Encoder().encode(data);
//
//        System.out.println(encode);
//
//        byte[] data1 = new BASE64Decoder().decodeBuffer(encode);
//
//        Files.write(Paths.get("C:\\Users\\ZhangShaowei\\Desktop\\2.jpg"), data1, StandardOpenOption.CREATE_NEW);

    }

    @Data
    @AllArgsConstructor
    static class A {
        protected String id;
    }

    @Data
    static class B extends A {
        private String name;

        public B(String id, String name) {
            super(id);
            this.name = name;
        }
    }

    @Data
    static class C {
        private String name;
    }

}
