package com;

import com.zsw.stream.CustomProcessor;
import com.zsw.stream.CustomSource;
import com.zsw.stream.CustomSink;
import com.zsw.stream.client.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.*;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author ZhangShaowei on 2019/9/23 14:14
 *
 * Source.class, CustomSource.class, CustomSink.class
 **/
@Slf4j
@RestController
@EnableBinding({CustomProcessor.class})
@SpringBootApplication
public class ServiceProvider {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ServiceProvider.class, args);

        CustomSource source = applicationContext.getBean(CustomSource.class);
        MessageChannel messageChannel = source.output();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while (true) {
                line = reader.readLine();
                if (StringUtils.isEmpty(line)) {
                    continue;
                }
                if ("exit".equalsIgnoreCase(line)) {
                    break;
                }
                try {
                    Person person = Person.builder().name(line).address("Earth").age(18).build();
                    boolean send = messageChannel.send(MessageBuilder.withPayload(person).build());
                    if (!send) {
                        log.warn("send message faild: person = {}, send = {}", person, send);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        applicationContext.close();
    }

    @StreamListener(CustomProcessor.INPUT)
    public void handle(Person person) {
        log.info("Echo from client: {}", person);
    }


    /**
     * 通过 Spring Message API 监听数据
     *
     * @return
     */
//    @Bean
//    public ApplicationRunner runner(CustomSink customSink) {
//        return args -> {
//            customSink.channel().subscribe(new MessageHandler() {
//                @Override
//                public void handleMessage(Message<?> message) throws MessagingException {
//                    MessageHeaders headers = message.getHeaders();
//                    String contentType = headers.get("Content-Type", String.class);
//                    Object object = message.getPayload();
//                    System.out.printf("收到消息[主体：%s , 消息头：%s \n", object, headers);
//                }
//            });
//        };
//    }


}
