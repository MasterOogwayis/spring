package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This is a resource server.
 *
 * @author ZhangShaowei on 2018/1/26 14:20
 **/
@SpringBootApplication
@EnableResourceServer
public class ResourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResourceApplication.class, args);
    }

}
