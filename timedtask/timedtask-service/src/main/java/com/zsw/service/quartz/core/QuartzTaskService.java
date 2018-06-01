package com.zsw.service.quartz.core;

import com.zsw.base.utils.DateUtils;
import com.zsw.persistence.timedtask.bean.TimedTask;
import com.zsw.service.timedtask.TimedTaskService;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;

/**
 * QuartzTaskService
 *
 * @author ZhangShaowei on 2018/4/19 15:52
 **/
public class QuartzTaskService implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * logger
     */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     *
     */
    private SchedulerFactoryBean schedulerFactoryBean;

    /**
     *
     */
    private TimedTaskService timedTaskService;


    /**
     * true 存在 false 不存在
     *
     * @param timedTask TimedTask
     * @return true or false
     */
    public boolean checkJob(TimedTask timedTask) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(timedTask.getMark(), Scheduler.DEFAULT_GROUP);
        try {
            if (scheduler.checkExists(triggerKey)) {
                return true;
            }
        } catch (SchedulerException e) {
//            e.printStackTrace();
            logger.error("检查定时任务出错：{}", e.getMessage());
        }
        return false;
    }


    /**
     * 开启
     */
    public boolean startJob(TimedTask timedTask) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            Class clazz = Class.forName(timedTask.getMark());
            JobDetail jobDetail = JobBuilder.newJob(clazz).build();
            // 触发器
            TriggerKey triggerKey = TriggerKey.triggerKey(timedTask.getMark(), Scheduler.DEFAULT_GROUP);
            CronTrigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity(triggerKey)
                    .withSchedule(CronScheduleBuilder.cronSchedule(timedTask.getCron())).build();
            scheduler.scheduleJob(jobDetail, trigger);
            // 启动
            if (!scheduler.isShutdown()) {
                scheduler.start();
                logger.info("---任务[{}]启动成功-------", triggerKey.getName());
                return true;
            } else {
                logger.info("---任务[{}]已经运行，请勿再次启动-------", triggerKey.getName());
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return false;
    }

    /**
     * @param timedTask
     * @return
     */
    public boolean updateJob(TimedTask timedTask) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        TriggerKey triggerKey = TriggerKey.triggerKey(timedTask.getMark(), Scheduler.DEFAULT_GROUP);
        try {
            if (scheduler.checkExists(triggerKey)) {
                return false;
            }

            JobKey jobKey = JobKey.jobKey(timedTask.getMark(), Scheduler.DEFAULT_GROUP);

            CronScheduleBuilder schedBuilder = CronScheduleBuilder.cronSchedule(timedTask.getCron())
                    .withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(triggerKey)
                    .withDescription(DateUtils.now()).withSchedule(schedBuilder).build();
            Class clazz = null;
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            HashSet<Trigger> triggerSet = new HashSet<>();
            triggerSet.add(trigger);
            scheduler.scheduleJob(jobDetail, triggerSet, true);
            logger.info("---任务[{}]更新成功-------", triggerKey.getName());
            return true;
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * @param timedTask
     * @return
     */
    public boolean remove(TimedTask timedTask) {
        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        TriggerKey triggerKey = TriggerKey.triggerKey(timedTask.getMark(), Scheduler.DEFAULT_GROUP);
        try {
            if (checkJob(timedTask)) {
                scheduler.pauseTrigger(triggerKey);
                scheduler.unscheduleJob(triggerKey);
                scheduler.deleteJob(JobKey.jobKey(timedTask.getMark(), Scheduler.DEFAULT_GROUP));
                logger.info("---任务[{}]删除成功-------", triggerKey.getName());
                return true;
            }
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     *
     */
    public void initJobs() {
        TimedTask timedTask = new TimedTask();
        timedTask.setStatus(TimedTask.STATUS_ENABLED);
        List<TimedTask> tasks = this.timedTaskService.findAll(timedTask);
        if (CollectionUtils.isEmpty(tasks)) {
            this.logger.info("无定时任务启动......");
            return;
        }
        tasks.forEach(this::startJob);
    }

    public QuartzTaskService(SchedulerFactoryBean schedulerFactoryBean, TimedTaskService timedTaskService) {
        this.schedulerFactoryBean = schedulerFactoryBean;
        this.timedTaskService = timedTaskService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initJobs();
    }
}
