package com.zsw;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author ZhangShaowei on 2020/12/29 15:04
 */
//@Slf4j
//@EnableHystrix
//@EnableFeignClients
//@EnableAsync
//@EnableDiscoveryClient
@EnableCaching
@SpringBootApplication
public class SimpleTestsApp {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        new SpringApplicationBuilder(SimpleTestsApp.class)
                .applicationStartup(new BufferingApplicationStartup(1024 * 20))
                .web(WebApplicationType.SERVLET)
                .run(args);
//        ConfigurableApplicationContext applicationContext = SpringApplication.run(SimpleTestsApp.class, args);
//        ApiController bean = applicationContext.getBean(ApiController.class);
//        System.err.println(bean.hello("123"));
//        System.out.println(bean.t(new Customer()));
//        System.out.println(bean.t2(new Customer(), null));
//        System.out.println(bean.t3(null, new Customer()));
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
//        ConfigurableApplicationContext run = new SpringApplicationBuilder()
//                .sources(SimpleTestsApp.class)
//                .web(WebApplicationType.NONE)
//                .run(args);
//        MessageClient bean = run.getBean(MessageClient.class);
//        for (int i = 0; i < 100; i++) {
//            String send;
//            if ((i & 1) == 0) {
//                send = bean.prometheus();
//            } else {
//                send = bean.prometheus1();
//            }
//            log.info("response: {}", send);
//        }
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
