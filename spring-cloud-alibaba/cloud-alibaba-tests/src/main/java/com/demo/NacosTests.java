package com.demo;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2021/3/29 10:38
 */
@Slf4j
@SpringBootApplication
public class NacosTests {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
//        SpringApplication.run(NacosTests.class, args);
        ConfigurableApplicationContext applicationContext
                = new SpringApplicationBuilder()
                .sources(NacosTests.class)
                .web(WebApplicationType.NONE).run(args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        try {
            while (true) {
                String host = environment.getProperty("spring.redis.host");
                log.info("spring.redis.host: {}", host);
                TimeUnit.SECONDS.sleep(5);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @SneakyThrows
    public static void test() {
        String serverAddr = "192.168.1.13:8848";
        String dataId = "alt-navigation-api-dev.yml";
        String group = "DEFAULT_GROUP";

        Properties properties = new Properties();
        properties.put("serverAddr", serverAddr);
        ConfigService configService = NacosFactory.createConfigService(properties);
        String config = configService.getConfig(dataId, group, TimeUnit.SECONDS.toMillis(5));


        log.info("config = {}", config);

    }

}
