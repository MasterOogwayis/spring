package com.zsw;

import com.zsw.orm.redis.utils.RedisHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

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
