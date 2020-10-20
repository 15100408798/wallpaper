package com.yushang.wallpaper.common.config.quartz;
import com.yushang.wallpaper.common.utils.log.LoggerUtils;
import com.yushang.wallpaper.layer.service.store.ShopService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ShopWeekMoney implements Job {

    private static ShopService shopService;

    @Configuration
    static class DefinedShopService{

        @Autowired
        private ShopService shopService;

        @Bean
        public void setShopService(){
            ShopWeekMoney.shopService = shopService;
        }
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try{
            shopService.saveShopOneWeek();
        }catch (Exception e){
            LoggerUtils.error("生成周统计数据失败",e);
        }
    }

}
