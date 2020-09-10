package com.zsw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author ZhangShaowei on 2020/5/11 14:15
 */
@Slf4j
//@EnableScheduling
//@EnableDiscoveryClient
@SpringBootApplication
public class SimpleApi {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
//        ConfigurableApplicationContext applicationContext = SpringApplication.run(SimpleApi.class, args);
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder()
                .sources(SimpleApi.class)
                .web(WebApplicationType.NONE)
                .run(args);

        log.info("main");
    }

    /**
     * 实际上这种方式会阻塞 {@link org.springframework.boot.SpringApplication#run(String...)}的完成
     *
     * @return
     */
    @Bean
    public CommandLineRunner dontKillMe() {
        return args -> {
            log.info("dontKillMe");
            Thread.currentThread().join();
        };
    }

//    @Scheduled(cron = "0/5 * * * * *")
//    public void t() {
//        System.out.println(System.currentTimeMillis() + "ms");
//    }

}
