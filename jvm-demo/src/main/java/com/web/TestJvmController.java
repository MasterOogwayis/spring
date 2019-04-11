package com.web;

import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;

/**
 * -Xms64m -Xmx64m -Xmn32m -XX:+PrintGCDetails -XX:+UseG1GC -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\Users\ZhangShaowei\Desktop\dump
 * -Xms128m -Xmx128m -Xmn64m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=C:\Users\Administrator\Desktop
 *
 * @author ZhangShaowei on 2019/2/25 9:40
 **/
@Slf4j
@RestController
public class TestJvmController {

    @Autowired
    private Executor executor;

    @SneakyThrows
    @GetMapping("printl")
    public Object printl(@RequestParam("msg") String msg) {
        System.out.println(msg);
        log.info(msg);
        log.debug(msg);
        log.error(msg);
        return "success";
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

    @PostMapping("test")
    public Object post(@RequestBody IdParam<Long> idParam) {
        this.executor.execute(()->{
            for (int i = 0; i < 100; i++) {
                log.info("time {}", System.currentTimeMillis());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        return idParam;
    }


    @GetMapping("sc")
    @SneakyThrows
    public String sc()  {
        for (int i = 0; i < 20; i++) {
            this.executor.execute(() -> {
                byte[] bytes;
                try {
                    for (int j = 0; j < 1000; j++) {
                        bytes = new byte[random(128, 1024)];
                        Thread.sleep(random(1, 20));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        return "success";
    }


    private int random(int min, int max){
        Random random = new Random();
        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;

    }


}
