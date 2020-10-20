package com.yushang.wallpaper.common.config.quartz;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

/**
 * 任务调度处理
 */
@Configuration
public class DefinedQuartz {

    /**
     * 任务调度
     */
    @Autowired
    private Scheduler scheduler;

    /**
     * 开始执行任务
     */
    public void startJob() throws SchedulerException {
        schedulerJob(scheduler);
        scheduler.start();
        System.out.println("任务开始执行");
    }

    /**
     * 任务调度处理
     * 实例化Job，将任务触发器加入到任务调度中
     */
    private void schedulerJob(Scheduler scheduler) throws SchedulerException {
        //通过JobBuilder构建JobDetail实例，JobDetail规定只能是实现Job接口的实例
        JobDetail jobDetail = JobBuilder.newJob(ShopWeekMoney.class).withIdentity("job1", "group1").build();

        //基于表达式构建触发器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 0 0 ? * MON");

        //CronTrigger表达式触发器 继承于Trigger
        //TriggerBuilder用于构建触发器实例
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("job1", "group1").withSchedule(cronScheduleBuilder).build();

        scheduler.scheduleJob(jobDetail,cronTrigger);

    }




}
