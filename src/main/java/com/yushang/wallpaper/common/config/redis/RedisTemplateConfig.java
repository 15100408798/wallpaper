package com.yushang.wallpaper.common.config.redis;

import com.yushang.wallpaper.common.config.Shiro.cache.DefinedRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class RedisTemplateConfig {


	@Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        DefinedRedisSerializer definedRedisSerializer = new DefinedRedisSerializer();
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(definedRedisSerializer);
        template.setValueSerializer(definedRedisSerializer);
        template.setHashKeySerializer(definedRedisSerializer);
        template.setHashValueSerializer(definedRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }



}
