package com;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.SneakyThrows;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * test
 *
 * @author ZhangShaowei on 2018/4/3 10:05
 **/
public abstract class StaticTests {

//    private static final Gson gson = new GsonBuilder()
//            .setLenient()
//            .setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    protected static final Gson gson = new GsonBuilder()
            .setLenient()
            .enableComplexMapKeySerialization()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .disableHtmlEscaping()
            .create();

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

    public static AtomicInteger atomicInteger = new AtomicInteger(0);

    public static void increase(){
        atomicInteger.incrementAndGet();
    }

    @SneakyThrows
    public static void main(String[] args) {


        int num = 20;

        List<Thread> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            Thread thread = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
            });
            list.add(thread);
            thread.start();
        }

        while (Thread.activeCount() > 2) {
            System.out.println(Thread.activeCount());
            Thread.yield();
            Thread.sleep(1000);
        }
        System.out.println(atomicInteger);



    }

    @Test
    public void test() {
        A a = new A();
        a.setA("a");
        a.setB(1);
        System.out.println(gson.toJson(a));
    }


    @Data
    static class A {
        public String a;
        public int b;
    }


}
