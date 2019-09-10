package com;

import com.aliyun.openservices.ons.api.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author ZhangShaowei on 2019/9/5 17:25
 **/
@SpringBootApplication
public class RocketMQApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(RocketMQApp.class, args);
        RocketMQTemplate rocketMQTemplate = applicationContext.getBean(RocketMQTemplate.class);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            String line;
            while (true) {
                line = reader.readLine();
                if (StringUtils.isEmpty(line)) {
                    continue;
                }
                try {
                    SendResult sendResult = rocketMQTemplate.syncSend("BCPT_SC_TEST", new GenericMessage<>(line));
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
