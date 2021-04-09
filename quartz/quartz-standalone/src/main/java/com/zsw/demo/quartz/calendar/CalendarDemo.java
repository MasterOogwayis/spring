package com.zsw.lesson.quartz.calendar;

import com.zsw.lesson.quartz.job.MyJob1;
import com.zsw.lesson.quartz.listener.MyJobListener;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.calendar.AnnualCalendar;
import org.quartz.impl.matchers.EverythingMatcher;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author Administrator on 2019/10/27 14:47
 **/
@Slf4j
public class CalendarDemo {

    @SneakyThrows
    public static void main(String[] args) {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.getListenerManager().addJobListener(new MyJobListener(), EverythingMatcher.allJobs());

        scheduler.start();

        // 定义日历
        AnnualCalendar holidays = new AnnualCalendar();
        Calendar guoqingjie = new GregorianCalendar(2019, 10, 1);
        holidays.setDayExcluded(guoqingjie, true);
        // 排除中秋节
        Calendar midAutumn = new GregorianCalendar(2019, 9, 13);
        holidays.setDayExcluded(midAutumn, true);
        // 排除圣诞节
        Calendar christmas = new GregorianCalendar(2019, 12, 25);
        holidays.setDayExcluded(christmas, true);

        // 调度器添加日历
        scheduler.addCalendar("holidays", holidays, false, false);

        JobDetail jobDetail = JobBuilder.newJob(MyJob1.class)
                .withIdentity("job1", "group1")
                .usingJobData("zsw", "Shaowei Zhang")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .modifiedByCalendar("holidays")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2).repeatForever())
                .build();

        Date firstRunDate = scheduler.scheduleJob(jobDetail, trigger);
        log.info("{} 第一次触发: {}", jobDetail.getKey(), firstRunDate);


    }

}
