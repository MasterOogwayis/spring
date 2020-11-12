package com.zsw.test.api;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author ZhangShaowei on 2020/11/11 17:02
 */
@Slf4j
@Service
public class ScheduledServeice {

    @SneakyThrows
    @Scheduled(fixedDelayString = "${jobs.work:10}")
    public void work() {
        log.info("incoming ...");
        TimeUnit.SECONDS.sleep(5);
        log.info("work: {}", LocalDateTime.now());
    }

}
