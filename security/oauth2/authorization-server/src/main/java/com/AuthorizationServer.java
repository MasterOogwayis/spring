package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * AuthorizationServerSecurityConfiguration
 *
 * @author ZhangShaowei on 2017/12/26 9:35
 */

@SpringBootApplication
//@EnableDiscoveryClient
public class AuthorizationServer {


    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServer.class, args);
    }


}
