package com.yushang.wallpaper;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@MapperScan(value = {"com.yushang.wallpaper.common.mapper"})        //扫描mapper接口所在的路径
@EnableTransactionManagement        //开启事务
@EnableAsync
public class WallpaperApplication {

    public static void main(String[] args) {
    	SpringApplication.run(WallpaperApplication.class,args);
    	log.info("==========================================");
    	log.info("============= 项目启动成功 ===============");
    	log.info("==========================================");
    }

}

