package org.yangxin.test.timedtask.quartz;

import lombok.Data;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Job样例
 *
 * @author yangxin
 * 2019/11/15 17:03
 */
@Data
public class JobTest implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        // 打印当前的执行时间，格式为2017-01-01 00:00:00
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Exec Time Is: " + simpleDateFormat.format(new Date()));

    }
}
