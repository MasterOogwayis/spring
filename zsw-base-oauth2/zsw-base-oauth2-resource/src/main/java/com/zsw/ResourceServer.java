package com.zsw;

import com.zsw.base.oauth2.annotation.EnableZswResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZhangShaowei on 2020/9/11 15:28
 */
@EnableZswResourceServer
@SpringBootApplication
public class ResourceServer {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(ResourceServer.class, args);
    }

}
