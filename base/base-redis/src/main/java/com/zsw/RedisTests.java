package com.zsw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ZhangShaowei on 2021/3/30 9:23
 */
@Slf4j
@SpringBootApplication
public class RedisTests {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
//        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder()
//                .sources(RedisTests.class)
//                .web(WebApplicationType.NONE)
//                .run(args);
        SpringApplication.run(RedisTests.class, args);

    }

}
