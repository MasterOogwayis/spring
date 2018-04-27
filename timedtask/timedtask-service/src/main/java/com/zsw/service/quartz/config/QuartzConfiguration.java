package com.zsw.service.quartz.config;

import com.zsw.service.quartz.core.JobFactory;
import com.zsw.service.quartz.core.QuartzTaskService;
import com.zsw.service.timedtask.TimedTaskService;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * QuartzConfiguration
 *
 * @author ZhangShaowei on 2018/4/19 15:53
 **/
@Configuration
public class QuartzConfiguration {


    @Bean
    public JobFactory jobFactory(AutowireCapableBeanFactory capableBeanFactory) {
        return new JobFactory(capableBeanFactory);
    }


    /**
     * @return
     */
    @Bean(value = "qScheduler", autowire = Autowire.NO)
    public SchedulerFactoryBean schedulerFactoryBean(JobFactory jobFactory) {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(jobFactory);
        return schedulerFactoryBean;
    }

    /**
     * @param schedulerFactoryBean
     * @param timedTaskService
     * @return
     */
    @Bean
    public QuartzTaskService jobTask(SchedulerFactoryBean schedulerFactoryBean, TimedTaskService timedTaskService) {
        return new QuartzTaskService(schedulerFactoryBean, timedTaskService);
    }


}
