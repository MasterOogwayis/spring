package com.web;

import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
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
        for (int i = 0; i < 1000; i++) {
            byte[] data1 = new byte[1 * 1024 * 1024];

            Thread.sleep(100L);
        }

        return "success";
    }


}
