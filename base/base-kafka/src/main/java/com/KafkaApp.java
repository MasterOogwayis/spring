package com;

import com.zsw.kafka.service.ProducerService;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2019/8/20 13:47
 **/
@SpringBootApplication
public class KafkaApp {

    @SneakyThrows
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(KafkaApp.class, args);
//        ProducerService bean = applicationContext.getBean(ProducerService.class);
//
//        int i = 0;
//        while (true) {
//            bean.send("test", i++, "Hello " + i);
//            TimeUnit.SECONDS.sleep(1);
//        }

    }

}
