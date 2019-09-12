package com;

import com.zsw.mq.spring.api.SendResult;
import com.zsw.mq.spring.core.RocketMQTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author ZhangShaowei on 2019/9/5 17:25
 **/
@Slf4j
@SpringBootApplication
public class RocketMQApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(RocketMQApp.class, args);
        RocketMQTemplate rocketMQTemplate = applicationContext.getBean(RocketMQTemplate.class);

        log.info("waitting for input ...");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while (true) {
                line = reader.readLine();
                if (StringUtils.isEmpty(line)) {
                    continue;
                }
                try {
                    SendResult sendResult = rocketMQTemplate.syncSend(
                            "BCPT_CRM_TEST", MessageBuilder.withPayload(line).build());
                    System.out.println(sendResult);
                    System.err.println(sendResult.getMessageId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
