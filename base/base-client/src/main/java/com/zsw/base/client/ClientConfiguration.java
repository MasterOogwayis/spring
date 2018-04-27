package com.zsw.base.client;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

/**
 * @author ZhangShaowei on 2017/11/6 16:07
 */
@Configuration
@EnableFeignClients(basePackages = "com")
public class ClientConfiguration {
}
