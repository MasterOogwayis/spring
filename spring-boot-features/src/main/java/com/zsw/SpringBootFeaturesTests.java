package com.zsw;

import com.zsw.persistence.entity.TestUser;
import com.zsw.persistence.repository.TestUserRepository;
import com.zsw.persistence.repository.base.impl.BaseRepositoryImpl;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.Tuple;
import java.util.Collections;
import java.util.List;

/**
 * @author ZhangShaowei on 2021/8/18 11:28
 */
@EnableAspectJAutoProxy
@Slf4j
@EnableJpaAuditing(modifyOnCreate = false)
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
@EntityScan(basePackageClasses = TestUser.class)
@EnableTransactionManagement
@SpringBootApplication(proxyBeanMethods = false)
public class SpringBootFeaturesTests {

    /**
     * start server
     *
     * @param args args
     */
    @SneakyThrows
    public static void main(String[] args) {
//        SpringApplication.run(SpringBootFeaturesTests.class, args);
//        BufferingApplicationStartup applicationStartup = new BufferingApplicationStartup(1024 * 2);
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(SpringBootFeaturesTests.class)
//                .applicationStartup(applicationStartup)
//                .logStartupInfo(Boolean.TRUE)
//                .lazyInitialization(Boolean.TRUE)
//                .sources(SpringBootFeaturesTests.class)
                .properties(Collections.singletonMap("user.name", "Author"))
                .web(WebApplicationType.SERVLET)
                .run(args);

        TestUserRepository repository = applicationContext.getBean(TestUserRepository.class);
        List<Tuple> available = repository.findAvailable();
        System.out.println(available);
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

//    @Bean
//    public ExitCodeGenerator exitCodeGenerator() {
//        return () -> 42;
//    }


//    @Bean
//    public CommandLineRunner commandLineRunner() {
//        return args -> log.info("CommandLineRunner run ... args = {}", Arrays.toString(args));
//    }
//
//    @Bean
//    public ApplicationRunner applicationRunner() {
//        return args -> log.info("ApplicationRunner run ... args = {}", args);
//    }


}
