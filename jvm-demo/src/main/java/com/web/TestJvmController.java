package com.web;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * -Xms64m -Xmx64m -Xmn32m -XX:+PrintGCDetails -XX:+UseG1GC
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
        for (int i = 0; i < 1000000; i++) {
            byte[] data = new byte[1024];
            Thread.sleep(1L);
        }
        return "success";
    }


}
