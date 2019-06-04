package com;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZhangShaowei on 2019/6/4 15:32
 **/
@SpringBootApplication
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class NacosApp {


    public static void main(String[] args) {
        SpringApplication.run(NacosApp.class, args);
    }

}
