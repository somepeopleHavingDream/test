package org.yangxin.test.quartz;

import lombok.Data;
import org.quartz.*;

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
    private String message;
    private Float floatJobValue;
    private Double doubleTriggerValue;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) {
        // 打印当前的执行时间，格式为2017-01-01 00:00:00
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("Current Exec Time Is: " + simpleDateFormat.format(new Date()));

//        // 编写具体的业务逻辑
////        System.out.println("Hello World!");
//        // Job
////        JobKey jobKey = jobExecutionContext.getJobDetail().getKey();
////        System.out.println(jobKey.getName() + ":" + jobKey.getGroup());
////
////        // Trigger
////        TriggerKey triggerKey = jobExecutionContext.getTrigger().getKey();
////        System.out.println(triggerKey.getName() + ":" + triggerKey.getGroup());
//
//        // JobDataMap(JobDetail)
////        JobDataMap jobDetailJobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
////        JobDataMap triggerJobDataMap = jobExecutionContext.getTrigger().getJobDataMap();
////        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
////        String message = mergedJobDataMap.getString("message");
////        float floatJobValue = mergedJobDataMap.getFloat("FloatJobValue");
////        String triggerMessage = mergedJobDataMap.getString("message");
////        double doubleTriggerValue = mergedJobDataMap.getDouble("DoubleTriggerValue");
//
//        System.out.println(message);
//        System.out.println(floatJobValue);
////        System.out.println(triggerMessage);
//        System.out.println(doubleTriggerValue);
    }
}
