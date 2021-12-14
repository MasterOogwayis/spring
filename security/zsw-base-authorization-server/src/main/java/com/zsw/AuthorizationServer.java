package com.zsw;

import com.zsw.base.oauth2.annotation.EnableZswAuthorizationServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author ZhangShaowei on 2020/9/10 17:18
 */
@EnableFeignClients(basePackages = {"com.zsw"})
@EnableDiscoveryClient
@SpringBootApplication
@EnableZswAuthorizationServer
public class AuthorizationServer {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServer.class, args);
    }


}
