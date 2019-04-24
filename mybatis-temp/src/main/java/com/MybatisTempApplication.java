package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zsw.persistence.dao")
public class MybatisTempApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisTempApplication.class, args);
    }

}
