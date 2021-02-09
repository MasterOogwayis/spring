package com.zsw;

import com.zsw.client.MessageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ZhangShaowei on 2020/12/29 15:04
 */
@Slf4j
@EnableHystrix
@EnableFeignClients
@SpringBootApplication
public class SimpleTestsApp {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
//        ConfigurableApplicationContext applicationContext = SpringApplication.run(RocketMQApp.class, args);
//        RocketMQTemplate rocketMQTemplate = applicationContext.getBean(RocketMQTemplate.class);
//
//        log.info("waitting for input ...");
//        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
//            String line;
//            while (true) {
//                line = reader.readLine();
//                if (StringUtils.isEmpty(line)) {
//                    continue;
//                }
//                try {
//                    SendResult sendResult = rocketMQTemplate.syncSend(
//                            "ZSW_CRM_TEST", MessageBuilder.withPayload(line).build());
//                    System.out.println(sendResult);
//                    System.err.println(sendResult.getMessageId());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        ConfigurableApplicationContext run = new SpringApplicationBuilder()
                .sources(SimpleTestsApp.class)
                .web(WebApplicationType.NONE)
                .run(args);
        MessageClient bean = run.getBean(MessageClient.class);
        Map<String, Object> map = new HashMap<>();
        map.put("alias", "18380160980");
        map.put("content", "This is a test");
        map.put("extra", Collections.singletonMap("type", 1));
        for (int i = 0; i < 10; i++) {
            String send;
            if ((i & 1) == 0) {
                send = bean.send(map);
            } else {
                send = bean.send1(map);
            }
            log.info("response: {}", send);
        }
//        ConfigurableApplicationContext run = SpringApplication.run(SimpleTestsApp.class, args);
//        GenericApplicationContext context = (GenericApplicationContext) run;
//        context.registerBean("helloController", HelloBean.class, HelloBean::new, bd -> {
//            bd.getPropertyValues().add("message", "This is a test");
//        });
//
////        BeanDefinition beanDefinition = new RootBeanDefinition(Hello.class);
////        beanDefinition.getPropertyValues().add("message", "This is a test");
////        context.registerBeanDefinition("hello", beanDefinition);
//
//        HelloBean hello = context.getBean(HelloBean.class);
//        hello.hello("zsw");
    }


}
