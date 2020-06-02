package com.zsw.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Files;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * @author ZhangShaowei on 2020/4/30 16:43
 */
public class StaticTests {

    private static boolean isAlive;

    private static final Pattern p  = Pattern.compile("^\\d$");

    public static void main(String[] args) {
        String data = "{\"isSuccess\": true, \"name\": \"zsw\"}";
        JSONObject json = JSON.parseObject(data);
        System.out.println(json.getBoolean("isSuccess"));
        System.err.println(json.getInteger("isSuccess"));


        Files.readAllLines()


    }


    @Data
    @AllArgsConstructor
    static class Ci {
        private String conditions;
        private String name;
        private String value;
    }

}
