package com.zsw.demo.quartz.job;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator on 2019/10/27 14:52
 **/
@Slf4j
public class MyJob2 implements Job {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    @SneakyThrows
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("job2 running at {}, needs {}s to execute ...", LocalDateTime.now().format(FORMATTER), 15);
        TimeUnit.SECONDS.sleep(15);
    }
}
