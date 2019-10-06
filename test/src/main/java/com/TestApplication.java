package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZhangShaowei on 2019/9/19 13:46
 **/
@SpringBootApplication
public class TestApplication {

    public static void main(String[] args) {
//        new SpringApplicationBuilder().main(TestApplication.class)
//                .web(WebApplicationType.NONE)
//                .build()
//                .run(args);
        SpringApplication.run(TestApplication.class, args);
    }



}
