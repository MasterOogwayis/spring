package com;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.NonNull;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * test
 *
 * @author ZhangShaowei on 2018/4/3 10:05
 **/
public class StaticTests {

    private static final Gson gson = new GsonBuilder()
            .setLenient()
            .serializeNulls()
            .setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    /**
     * jackson的json转换器
     */
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 初始化mapper参数
     */
    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //忽略没有的属性
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true); //允许没有双引号
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true); //允许转义字符
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true); //允许单引号
        mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }


    private static final String RANDOM_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    private static final java.util.Random RANDOM = new java.util.Random();

    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";


    public static void main(String[] args) throws Exception {
        List<A> list = new ArrayList<>();
        list.add(new A() {
        });

        for (final A a : list) {
            System.out.println(a);
        }

//        ConcurrentHashMap<String, Long> map = new ConcurrentHashMap<>();
//        System.out.println(map.putIfAbsent("key", 1L));
//        System.out.println(map.putIfAbsent("key", 2L));
//        System.err.println(map);

//        String path = "C:\\Users\\ZhangShaowei\\Desktop";
//        byte[] data = Files.readAllBytes(Paths.get(path, "1.txt"));
//        Files.write(Paths.get(path, "1.jpg"), Base64.decodeBase64(data), StandardOpenOption.CREATE);
    }

    static class A {
        static {
            System.out.println(2);
        }

        void say(String msg){

        };
    }

    public static byte[] hmacSHA1Encrypt(String body, String key) throws Exception {
        byte[] data = key.getBytes(ENCODING);
        //根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
        SecretKey secretKey = new SecretKeySpec(data, MAC_NAME);
        //生成一个指定 Mac 算法 的 Mac 对象
        Mac mac = Mac.getInstance(MAC_NAME);
        //用给定密钥初始化 Mac 对象
        mac.init(secretKey);

        //完成 Mac 操作
        return mac.doFinal(body.getBytes(ENCODING));
    }
//
//    public static <T> Dto<T> toDto(String body, Class<T> clazz) {
////        Type type = new TypeToken<Dto<T>>() {
////        }.getType();
//        Type type = new ParameterizedType() {
//            @Override
//            public Type[] getActualTypeArguments() {
//                return new Type[]{clazz};
//            }
//
//            @Override
//            public Type getRawType() {
//                return Dto.class;
//            }
//
//            @Override
//            public Type getOwnerType() {
//                return null;
//            }
//        };
//        Dto<T> dto = gson.fromJson(body, type);
//        return dto;
//    }


    @Data
    static class Dto {
        private String data;

        @NonNull
        private String name;
    }

    @Data
    static class B {
        private Integer age;
    }

}
