package com.web;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xms64m -Xmx64m -Xmn32m -XX:+PrintGCDetails -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\Users\ZhangShaowei\Desktop\dump
 *
 * @author ZhangShaowei on 2019/2/25 9:40
 **/
@SpringBootApplication
@RestController
public class TestJvmController {

    public static void main(String[] args) {
        SpringApplication.run(TestJvmController.class, args);
    }


    @SneakyThrows
    @GetMapping("test")
    public Object test() {
        List<Object> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new byte[1024 * 1024]);
        }
        return "success";
    }

    @GetMapping("dead")
    public Object dead() {
        return testDead("dead");
    }

    private String testDead(String str) {
        return testDead(str);
    }


}
