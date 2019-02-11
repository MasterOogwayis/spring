package com;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.zsw.AESUtil;
import lombok.Builder;
import lombok.Data;
import lombok.SneakyThrows;
import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


/**
 * test
 *
 * @author ZhangShaowei on 2018/4/3 10:05
 **/
public abstract class StaticTests {

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


    @SneakyThrows
    public static void main(String[] args) {

//        RestTemplate restTemplate = new RestTemplate();

//        String str = "YICqxIrgX/3UlP/CgQ1OuV6lLjhuQPuE8Rrjb5SyD8vKFdVTma7V9l48Qphu0qcO";
//        String json = AESUtil.decrypt(str, AESUtil.DEFAULT_KEY);
//        System.out.println(json);
//        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
//        System.err.println(jsonObject.get("mobile").toString());

        String str = "YICqxIrgX/3UlP/CgQ1OuZ5zUOCG6mfpc96rcSUuoZFLFeRfWWX8T9WaPP8qMX+RBV5LDIXxBoTJFlzE7Hhh2wtsm4Hs23lhw189Gg2PS7a59Ig6B/LblljfLFP0cJg1";
        System.out.println(AESUtil.decrypt(str, AESUtil.DEFAULT_KEY));

        JsonObject jsonObject = new JsonObject();
        jsonObject.

//        System.out.println(Math.ceil(12d * 0.035));

//        System.out.println(new String(Base64.decode("ZWFzeWxpbmVzLWVwYzplYXN5bGluZXMtZXBj".getBytes())));
//        System.err.println(new String(Base64.encode("easylines-epc-api:easylines-epc-api".getBytes())));

//
//        System.out.println(DateTimeFormatter.ISO_ZONED_DATE_TIME.parse(str));

//        System.out.println(ZonedDateTime.parse(str));
//        System.out.println(ZonedDateTime.parse("2018-12-29T12:00:00.0"));

//        MonthDay monthDay = MonthDay.now();
//        System.out.println(monthDay.getDayOfMonth());


//        Stream.of("17000000123", "17100000079", "17000000001", "17100000001", "18728002331")
//                .map(CiphertextUtils::encryption).forEach(System.out::println);

//        for (int i = 0; i < 100; i++) {
//            System.out.println((int) ((Math.random()*9+1)*1000));
//        }

//        JsonElement jsonElement = new JsonPrimitive("ASDASDSAD");
//        System.out.println(jsonElement.toString());
    }

    private static boolean sendSimpleMessageRetry() {
        int times = 0;
        while (times < 3) {
            times++;
            return true;
        }
        return false;
    }


    public static boolean shouldFilter(String requestURI) {
        boolean shouldFilter = true;
        List<String> list = Arrays.asList("/open/api", "/user/*", "/customer/info");
        for (String url : list) {
            if (org.apache.commons.lang.StringUtils.endsWith(url, "*")) {
                shouldFilter = !org.apache.commons.lang.StringUtils.startsWith(requestURI, StringUtils.replace(url, "*", ""));
            } else {
                shouldFilter = !Objects.equals(url, requestURI);
            }
            if (!shouldFilter) {
                break;
            }
        }
        return shouldFilter;
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

    @Data
    @Builder
    static class Dto {
        @SerializedName("t_name")
        private String name;
    }


}
