package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ZhangShaowei on 2017/9/15 13:42
 */
@SpringBootApplication
@EnableDiscoveryClient
//@EnablePrometheusEndpoint
//@EnableSpringBootMetricsCollector
public class ApiApplication {

    /**
     * main
     *
     * @param args args
     */
    public static void main(String[] args) {
//        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ApiApplication.class, args);
    }

}
