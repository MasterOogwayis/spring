package com.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Collections;
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
                .properties(Collections.singletonMap("spring.config.use-legacy-processing", Boolean.TRUE))
                .web(WebApplicationType.NONE).run(args);
        ConfigurableEnvironment environment = applicationContext.getEnvironment();
        try {
            while (true) {
                String name = environment.getProperty("config.name");
                log.info("config.name: {}", name);

                String key = environment.getProperty("config.key");
                log.info("config.key: {}", key);

                TimeUnit.SECONDS.sleep(2);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


//    @SneakyThrows
//    public static void test() {
//        String serverAddr = "192.168.1.13:8848";
//        String dataId = "alt-navigation-api-dev.yml";
//        String group = "DEFAULT_GROUP";
//
//        Properties properties = new Properties();
//        properties.put("serverAddr", serverAddr);
//        ConfigService configService = NacosFactory.createConfigService(properties);
//        String config = configService.getConfig(dataId, group, TimeUnit.SECONDS.toMillis(5));
//
//
//        log.info("config = {}", config);
//
//    }

}
