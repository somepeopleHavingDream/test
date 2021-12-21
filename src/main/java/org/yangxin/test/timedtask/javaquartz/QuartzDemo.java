package org.yangxin.test.timedtask.javaquartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.yangxin.test.timedtask.javaquartz.job.MyJob;
import org.yangxin.test.timedtask.javaquartz.listener.MyJobListener;
import org.yangxin.test.timedtask.javaquartz.listener.MySchedulerListener;
import org.yangxin.test.timedtask.javaquartz.listener.MyTriggerListener;

/**
 * @author yangxin
 * 2021/12/20 16:00
 */
public class QuartzDemo {

    public static void main(String[] args) throws SchedulerException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.getListenerManager().addSchedulerListener(new MySchedulerListener());

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    scheduler.shutdown();
                } catch (SchedulerException e) {
                    e.printStackTrace();
                }
            }
        });
        scheduler.start();

        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("jobDetail1", "group1")
                .usingJobData("name", "value")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .usingJobData("msg", "我是trigger触发的")
                .withPriority(2)
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")
                        .withMisfireHandlingInstructionFireAndProceed())
                .build();

        scheduler.getListenerManager().addTriggerListener(new MyTriggerListener());
        scheduler.getListenerManager().addJobListener(new MyJobListener());

        scheduler.scheduleJob(jobDetail, trigger);
        try {
            Thread.sleep(600000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.shutdown();
    }
}
