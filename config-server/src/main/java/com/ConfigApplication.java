package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author ZhangShaowei on 2017/4/28 14:10
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class, args);
    }

}
