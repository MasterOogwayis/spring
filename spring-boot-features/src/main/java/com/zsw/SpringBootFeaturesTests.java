package com.zsw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

/**
 * @author ZhangShaowei on 2021/8/18 11:28
 */
@Slf4j
@SpringBootApplication
public class SpringBootFeaturesTests {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        log.info("This is a test for Spring Boot Features.");
//        SpringApplication.run(SpringBootFeaturesTests.class, args);
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpringBootFeaturesTests.class)
//                .applicationStartup(applicationStartup)
//                .logStartupInfo(Boolean.TRUE)
//                .lazyInitialization(Boolean.TRUE)
//                .sources(SpringBootFeaturesTests.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }


    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> log.info("CommandLineRunner run ... args = {}", Arrays.toString(args));
    }

    @Bean
    public ApplicationRunner applicationRunner() {
        return args -> log.info("ApplicationRunner run ... args = {}", args);
    }


}
