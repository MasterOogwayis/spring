package com.zsw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author ZhangShaowei on 2020/5/11 14:15
 */
@Slf4j
@EnableScheduling
@SpringBootApplication
public class SimpleApi {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SimpleApi.class, args);
//        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder()
//                .sources(SimpleApi.class)
//                .web(WebApplicationType.NONE)
//                .run(args);
    }

    /**
     * 实际上这种方式会阻塞 {@link org.springframework.boot.SpringApplication#run(String...)}的完成
     *
     * @return
     */
//    @Bean
//    public CommandLineRunner dontKillMe() {
//        return args -> {
//            log.info("dontKillMe");
//            Thread.currentThread().join();
//        };
//    }

//    @Scheduled(cron = "0/5 * * * * *")
//    public void t() {
//        System.out.println(System.currentTimeMillis() + "ms");
//    }

}
