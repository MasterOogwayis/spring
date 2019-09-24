package com;

import com.zsw.stream.client.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.handler.annotation.SendTo;


/**
 * @author ZhangShaowei on 2019/9/23 16:45
 **/
@Slf4j
@EnableBinding(Processor.class)
@SpringBootApplication
public class ServiceClient {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(ServiceClient.class, args);

//        CustomSource source = applicationContext.getBean(CustomSource.class);
//        source.input().subscribe(new MessageHandler() {
//            @Override
//            public void handleMessage(Message<?> message) throws MessagingException {
//                log.info("received message: {}", message);
//            }
//        });
    }


    @StreamListener(Processor.INPUT)
    @SendTo(Processor.OUTPUT)
    public Object handle(Person person) {
        log.info("received message: {}", person);
        return person;
    }

}
