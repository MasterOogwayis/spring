package com.zsw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 测试各种配置方式
 *
 * @author ZhangShaowei on 2020/4/30 9:59
 * @see com.netflix.config.DynamicConfiguration
 * @see org.apache.commons.configuration.Configuration
 * @see org.springframework.core.env.Environment
 * @see org.springframework.cloud.config.client.ConfigServicePropertySourceLocator
 */
@EnableDiscoveryClient
@SpringBootApplication
public class TestConfiguration {

    public static void main(String[] args) {
        SpringApplication.run(TestConfiguration.class, args);
    }

}
