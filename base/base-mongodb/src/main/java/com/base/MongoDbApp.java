package com.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author ZhangShaowei on 2021/10/20 14:43
 */
@Slf4j
@SpringBootApplication
public class MongoDbApp {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(MongoDbApp.class, args);

//        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(MongoDbApp.class)
//                .web(WebApplicationType.NONE)
//                .run(args);
    }

}
