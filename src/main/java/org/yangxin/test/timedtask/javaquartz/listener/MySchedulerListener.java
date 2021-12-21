package org.yangxin.test.timedtask.javaquartz.listener;

import org.quartz.listeners.SchedulerListenerSupport;

import java.time.LocalTime;

/**
 * @author yangxin
 * 2021/12/20 16:01
 */
public class MySchedulerListener extends SchedulerListenerSupport {

    @Override
    public void schedulerStarting() {
        super.schedulerStarting();

        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + ",scheduler starting!");
    }

    @Override
    public void schedulerShuttingdown() {
        super.schedulerShuttingdown();

        LocalTime now = LocalTime.now();
        System.out.println(now.toString() + ",scheduler shutting down!");
    }
}
