package com.zsw;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

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
    public static void main(String[] args) throws InterruptedException {
//        BufferingApplicationStartup applicationStartup = new BufferingApplicationStartup(1024 * 20);
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
        JdbcTemplate jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        ExecutorService executor = applicationContext.getBean(ExecutorService.class);
        long timer = System.currentTimeMillis();
        String sql = "insert into t_data(`num`) values(?)";
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 10; i++) {
            int n = i;
            executor.execute(() -> {
                jdbcTemplate.execute((ConnectionCallback<Object>) con -> {
                    var preparedStatement = con.prepareStatement(sql);
                    for (int j = (n * 50_000) + 1; j <= (n + 1) * 50_000; j++) {
                        con.setAutoCommit(false);
                        preparedStatement.setInt(1, j & 1);
                        preparedStatement.addBatch();
                        if (j % 10000 == 0) {
                            preparedStatement.executeBatch();
                            con.commit();
                        }
                    }
                    return "success";

                });

                countDownLatch.countDown();
            });
        }
        countDownLatch.await();

//        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ps.setInt(1, i);
//            }
//
//            @Override
//            public int getBatchSize() {
//                return 1_000_000;
//            }
//        });

        log.info("cost: {}ms", System.currentTimeMillis() - timer);


    }

}
