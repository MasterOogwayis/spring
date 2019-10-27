package com.zsw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.zsw.quartz.persistence.mapper")
@SpringBootApplication
public class QuartzApp {

    public static void main(String[] args) {
        SpringApplication.run(QuartzApp.class, args);
    }
}
