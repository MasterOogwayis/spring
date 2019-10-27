package com.zsw.quartz.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.zsw.quartz.persistence.entity.SystemJob;
import com.zsw.quartz.service.SystemJobService;
import com.zsw.quartz.task.BaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 这个类用于启动SpringBoot时，加载作业。run方法会自动执行。
 * <p>
 * 另外可以使用 ApplicationRunner
 */
@Slf4j
@Component
public class InitStartSchedule implements CommandLineRunner {

    private Gson gson = new GsonBuilder().create();

    @Autowired
    private SystemJobService sysJobService;
    @Autowired
    private CustomJobFactory myJobFactory;

    @Override
    public void run(String... args) throws Exception {
        /**
         * 用于程序启动时加载定时任务，并执行已启动的定时任务(只会执行一次，在程序启动完执行)
         */

        //查询job状态为启用的
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("jobStatus", "1");
        List<SystemJob> jobList = sysJobService.querySysJobList(map);
        if (null == jobList || jobList.size() == 0) {
            log.info("系统启动，没有需要执行的任务... ...");
            return;
        }
        // 通过SchedulerFactory获取一个调度器实例
        SchedulerFactory sf = new StdSchedulerFactory();
        Scheduler scheduler = sf.getScheduler();
        // 如果不设置JobFactory，Service注入到Job会报空指针
        scheduler.setJobFactory(myJobFactory);
        // 启动调度器
        scheduler.start();

        for (SystemJob sysJob : jobList) {
            String jobClassName = sysJob.getJobName();
            String jobGroupName = sysJob.getJobGroup();
            //构建job信息
            JobDetail jobDetail = JobBuilder.newJob(getClass(sysJob.getJobClassPath()).getClass()).withIdentity(jobClassName, jobGroupName).build();
            if (!StringUtils.isEmpty(sysJob.getJobDataMap())) {
                Map<String, Object> dataMap = this.gson.fromJson(sysJob.getJobDataMap(), new TypeToken<Map<String, Object>>() {
                }.getType());
                for (Map.Entry<String, Object> m : dataMap.entrySet()) {
                    jobDetail.getJobDataMap().put(m.getKey(), m.getValue());
                }
            }
            //表达式调度构建器(即任务执行的时间)
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(sysJob.getJobCron());
            //按新的cronExpression表达式构建一个新的trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobClassName, jobGroupName)
                    .withSchedule(scheduleBuilder).startNow().build();
            // 任务不存在的时候才添加
            if (!scheduler.checkExists(jobDetail.getKey())) {
                try {
                    scheduler.scheduleJob(jobDetail, trigger);
                } catch (SchedulerException e) {
                    log.info("\n创建定时任务失败" + e);
                    throw new Exception("创建定时任务失败");
                }
            }
        }
    }

    public static BaseJob getClass(String classname) throws Exception {
        Class<?> c = Class.forName(classname);
        return (BaseJob) c.newInstance();
    }
}
