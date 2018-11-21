package com;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * test
 *
 * @author ZhangShaowei on 2018/4/3 10:05
 **/
public class StaticTests {

    private static final Gson gson = new GsonBuilder()
            .setLenient()
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

        String json = "{\"FULL_NAME\":\"name\"}";

        System.out.println(gson.fromJson(json, A.class));
        
//        System.out.println(System.currentTimeMillis());

//        JsonElement jsonBody = gson.fromJson("123", JsonElement.class);
//        System.out.println(jsonBody);
//        System.err.println(jsonBody.isJsonObject());
//        System.out.println(jsonBody.toString());

//        SortedMap<String, String> sortedMap = new TreeMap<>();
//        sortedMap.put("Notice", "11886");
//        sortedMap.put("SecretId", "289efb7512e54146273b982456b03f42ea93");
//        sortedMap.put("Timestamp", "1484718385");
//        sortedMap.put("content", "ew0KICAgICJQYXJhbWV0ZXIiOiAiMSINCn0=");
//        sortedMap.put("encryptCode", "0");
//        sortedMap.put("zipCode", "0");
//
//        sortedMap.keySet().forEach(System.out::println);

//        String data = "GETapi.ele-cloud.com/invoice/v1/interfaceTest?Nonce=11886&SecretId=289efb7512e54146273b982456b03f42ea93&Timestamp=1484718385&content=ew0KICAgICJQYXJhbWV0ZXIiOiAiMSINCn0=&encryptCode=0&zipCode=0";
//
//        String key = "27a06832a2214a4fa3b7105e4a72d370;";
//
//        byte[] encrypt = hmacSHA1Encrypt(data, key);
//
//        System.out.println(Base64.encodeBase64String(encrypt));

//        String body = "{\"data\":{\"name\"=\"name\",\"age\":1}}";
//        Dto<A> dto = toDto(body, A.class);
//        System.out.println(dto);
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

    public static <T> Dto<T> toDto(String body, Class<T> clazz) {
//        Type type = new TypeToken<Dto<T>>() {
//        }.getType();
        Type type = new ParameterizedType() {
            @Override
            public Type[] getActualTypeArguments() {
                return new Type[]{clazz};
            }

            @Override
            public Type getRawType() {
                return Dto.class;
            }

            @Override
            public Type getOwnerType() {
                return null;
            }
        };
        Dto<T> dto = gson.fromJson(body, type);
        return dto;
    }


    @Data
    @AllArgsConstructor
    static class Dto<T> {
        private T data;
    }

    @Data
    static class A {
        @SerializedName("FULL_NAME")
        private String name;
        private Integer age;
    }

    @Data
    static class B {
        private Integer age;
    }

}
