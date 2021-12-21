package org.yangxin.test.timedtask.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Scheduler样例
 *
 * @author yangxin
 * 2019/11/15 17:12
 */
public class SchedulerTest {

    public static void main(String[] args) {
        // 打印当前的执行时间，格式为2017-01-01 00:00:00
        Date currentTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Time Is: " + simpleDateFormat.format(currentTime));

        // 创建一个JobDetail实例，将该实例与JobTest.class绑定
        JobDetail jobDetail = JobBuilder.newJob(JobTest.class)
                .withIdentity("job1", "group1")
                .build();

        // 距离当前时间4秒钟之后首次执行任务，之后每隔两秒钟重复执行一次任务
        // 直到距离当前时间6秒钟之后为止
        CronTrigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("* * * * * ? *"))
                .build();

        // 创建Scheduler实例
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = stdSchedulerFactory.getScheduler();
            scheduler.start();

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
