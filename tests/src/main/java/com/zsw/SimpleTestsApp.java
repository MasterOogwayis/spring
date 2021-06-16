package com.zsw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author ZhangShaowei on 2020/12/29 15:04
 */
@Slf4j
//@EnableHystrix
//@EnableFeignClients
//@EnableAsync
//@EnableDiscoveryClient
//@EnableCaching
@SpringBootApplication(proxyBeanMethods = false)
public class SimpleTestsApp {


    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SimpleTestsApp.class, args);
//        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder()
//                .sources(SimpleTestsApp.class)
//                .web(WebApplicationType.SERVLET)
//                .run(args);
//        BufferingApplicationStartup applicationStartup = new BufferingApplicationStartup(1024 * 20);
//        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SimpleTestsApp.class)
//                .applicationStartup(applicationStartup)
//                .web(WebApplicationType.SERVLET)
//                .run(args);
//
//        StartupTimeline startupTimeline = applicationStartup.drainBufferedTimeline();
//        startupTimeline.getEvents()
//                .stream()
//                .sorted(Comparator.comparing(StartupTimeline.TimelineEvent::getDuration).reversed())
//                .limit(50)
//                .forEach(event -> {
//                    log.info("耗时: {}, startup name: {}",
//                            event.getDuration(), event.getStartupStep().getName());
//                    if (event.getStartupStep().getTags().iterator().hasNext()) {
//                        event.getStartupStep().getTags().forEach(t -> {
//                            log.info("key={}, value={}", t.getKey(), t.getValue());
//                        });
//                    }
//                });


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
