package com.zsw;

import com.zsw.service.dubbo.MathService;
import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author ZhangShaowei on 2020/8/31 13:47
 */
@DubboComponentScan(basePackageClasses = MathService.class)
@EnableDiscoveryClient
@SpringBootApplication
public class ProviderApp {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        SpringApplication.run(ProviderApp.class, args);
    }

}
