package org.yangxin.test.timedtask.javaquartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

import java.time.LocalTime;

/**
 * @author yangxin
 * 2021/12/20 16:13
 */
public class MyJobListener extends JobListenerSupport {

    @Override
    public String getName() {
        return "myJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        super.jobToBeExecuted(context);

        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + "job to be executed");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        super.jobWasExecuted(context, jobException);

        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + "job was executed");
    }
}
