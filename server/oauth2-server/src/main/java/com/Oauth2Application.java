package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * @author ZhangShaowei on 2017/12/26 9:35
 */

@SpringBootApplication
@EnableAuthorizationServer
public class Oauth2Application {


    public static void main(String[] args) {
        SpringApplication.run(Oauth2Application.class, args);
    }


}
