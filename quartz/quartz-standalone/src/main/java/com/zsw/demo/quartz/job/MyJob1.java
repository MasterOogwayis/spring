package com.zsw.demo.quartz.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator on 2019/10/27 14:52
 **/
@Slf4j
public class MyJob1 implements Job {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("bean={}, running job ... {}, zsw={}",
                this, this.atomicInteger.getAndIncrement(), jobExecutionContext.getJobDetail().getJobDataMap().get("zsw"));
    }
}
