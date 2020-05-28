package com.zsw.test.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangShaowei on 2020/5/11 14:18
 */
@Slf4j
@RequestMapping("simple")
@RestController
public class HelloWorldApi {

    private static final Gson gson = new GsonBuilder().create();

    @GetMapping("hello")
    public String hello(@RequestParam("name") String name, @RequestHeader("attr") String attr) {
        log.info("header: attr={}", attr);
        return "Hello " + name;
    }

    /**
     * @param username
     * @param attr
     * @return
     */
    @GetMapping("getUserInfo")
    public Map<String, String> getUserInfo(@RequestParam("username") String username, @RequestHeader("attr") String attr) {
        log.info("header: attr={}", attr);
        Map<String, String> map = new HashMap<>(4);
        map.put("username", username);
        map.put("address", "earth");
        return map;
    }


    private void test(String a, String b, String c) {
        System.out.println(a + b + c);
    }

}
