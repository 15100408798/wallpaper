package com.yushang.wallpaper.common.config.Shiro.session;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class RedisConfig {

    @Value("${redis.session.datasource}")
    public static int sessionDatasource;


}
