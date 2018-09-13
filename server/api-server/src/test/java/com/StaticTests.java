package com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.tomcat.util.buf.HexUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;


/**
 * test
 *
 * @author ZhangShaowei on 2018/4/3 10:05
 **/
public class StaticTests {

    private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();


    public static void main(String[] args) throws Exception {


        String imagePath = "C:\\Users\\ZhangShaowei\\Desktop\\1.jpg";

        byte[] data = Files.readAllBytes(Paths.get(imagePath));

        System.out.println( HexUtils.toHexString(data));

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
