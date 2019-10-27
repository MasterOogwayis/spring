package com.zsw.demo.quartz.listener;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

/**
 * @author Administrator on 2019/10/27 15:46
 **/
@Slf4j
public class MyJobListener extends JobListenerSupport {

    public static final String NAME = "_myJobListener";

    @Override
    public String getName() {
        log.info("Listener name = {}", NAME);
        return NAME;
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        log.info("jobToBeExecuted ...");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        log.info("jobExecutionVetoed ...");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        log.info("jobWasExecuted ...");
    }
}
