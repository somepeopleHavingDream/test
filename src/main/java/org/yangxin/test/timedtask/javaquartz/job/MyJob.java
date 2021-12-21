package org.yangxin.test.timedtask.javaquartz.job;

import lombok.Getter;
import lombok.Setter;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalTime;

/**
 * @author yangxin
 * 2021/12/20 16:05
 */
public class MyJob implements Job {

    @Setter
    @Getter
    private String msg;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime.toString() + ", msg=" + msg);
    }
}
