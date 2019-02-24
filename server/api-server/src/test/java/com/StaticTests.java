package com;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.SimpleDateFormat;


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


    public static void main(String[] args) {

        byte[] data = new byte[2 * 1024 * 1024];



//        System.out.println(StaticTests.class.getClassLoader().getParent().getParent());
//        System.out.println(StaticTests.class.getClassLoader().getParent());
//        System.out.println(StaticTests.class.getClassLoader());
//
//        System.out.println("--------------");
//
//        System.out.println(Object.class.getClassLoader());

//        long maxMemory = Runtime.getRuntime().maxMemory();
//        long totalMemory = Runtime.getRuntime().totalMemory();
//
//        System.out.println("max: " + (maxMemory / 1024 / 1024) + "mb");
//        System.out.println("total: " + (totalMemory / 1024 / 1024) + "mb");

    }

}
