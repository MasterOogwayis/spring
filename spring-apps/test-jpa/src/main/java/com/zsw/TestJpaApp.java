package com.zsw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.metrics.buffering.BufferingApplicationStartup;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author ZhangShaowei on 2020/9/25 13:58
 */
@Slf4j
@EntityScan
//@EnableJpaAuditing
@EnableJpaRepositories
@EnableTransactionManagement
@SpringBootApplication(proxyBeanMethods = false)
public class TestJpaApp {

    /**
     * start server
     *
     * @param args args
     */
    public static void main(String[] args) {
        BufferingApplicationStartup applicationStartup = new BufferingApplicationStartup(1024 * 20);
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(TestJpaApp.class)
//                .applicationStartup(applicationStartup)
//                .logStartupInfo(Boolean.TRUE)
//                .lazyInitialization(Boolean.TRUE)
                .web(WebApplicationType.SERVLET)
                .run(args);
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


    }

}
