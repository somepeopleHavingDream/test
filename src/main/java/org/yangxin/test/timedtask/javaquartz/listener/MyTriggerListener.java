package org.yangxin.test.timedtask.javaquartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.listeners.TriggerListenerSupport;

import java.util.Calendar;

/**
 * @author yangxin
 * 2021/12/20 16:09
 */
public class MyTriggerListener extends TriggerListenerSupport {

    @Override
    public String getName() {
        return "myTriggerListener";
    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        System.out.println("vetoJobExecution正在执行！");

        // 如果时间是5、15、25，尾数是5的时间点就不执行
        Calendar now = Calendar.getInstance();
        int second = now.get(Calendar.SECOND);
        if (second % 10 == 5) {
            System.out.println("本次Trigger不触发！");
            return true;
        }

        return false;
    }
}
