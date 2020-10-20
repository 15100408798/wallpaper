package com.yushang.wallpaper.common.config.quartz;

import com.yushang.wallpaper.common.utils.log.LoggerUtils;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * 监听spring容器加载完毕后事件，启动任务调用
 * 将Scheduler交给spring初始化管理
 */
@Configuration
public class ApplicationQuartzListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private DefinedQuartz definedQuartz;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try{
            System.out.println("调度器开始了");
            definedQuartz.startJob();
        }catch (Exception e){
            e.printStackTrace();
            LoggerUtils.error("启动任务调度器失败",e);
        }
    }


    @Bean
    public Scheduler scheduler() throws SchedulerException {
        StdSchedulerFactory schedulerFactory = new StdSchedulerFactory();
        return schedulerFactory.getScheduler();

    }


}
