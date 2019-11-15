package org.yangxin.test.quartz;

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

        // 开始时间
        Date startTime = new Date();
        startTime.setTime(currentTime.getTime() + 3000);
        // 结束时间
        Date endTime = new Date();
        endTime.setTime(currentTime.getTime() + 6000);

        // 创建一个JobDetail实例，将该实例与JobTest.class绑定
        JobDetail jobDetail = JobBuilder.newJob(JobTest.class)
                .withIdentity("job1", "group1")
//                .usingJobData("message", "job1")
//                .usingJobData("FloatJobValue", 3.14F)
                .build();

        System.out.println("jobDetail's name: " + jobDetail.getKey().getName());
        System.out.println("jobDetail's group: " + jobDetail.getKey().getGroup());
        System.out.println("jobDetail's jobClass: " + jobDetail.getJobClass().getName());

        // 创建一个Trigger实例，定义该job立即执行，并且每隔两秒钟重复执行一次，知道程序手动终止
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startAt(startTime)
                .endAt(endTime)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
//                .usingJobData("message", "trigger1")
//                .usingJobData("DoubleTriggerValue", 2.0D)
                .build();

        // 创建Scheduler实例
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = stdSchedulerFactory.getScheduler();
            scheduler.start();

//            // 打印当前的执行时间，格式为2017-01-01 00:00:00
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            System.out.println("Current Time Is: " + simpleDateFormat.format(new Date()));

            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
