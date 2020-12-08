package com.yushang.wallpaper.common.utils.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtils {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * VALUE 设置值
     *
     * @param key   REDIS-KEY
     * @param value REDIS-VALUE
     */
    public boolean setValueForOps(@NonNull String key, @NonNull Object value) {
        return setValueForOps(key, value, -1);
    }

    /**
     * VALUE 设置值
     *
     * @param key     REDIS-KEY
     * @param value   REDIS-VALUE
     * @param timeout 超时时间
     */
    public boolean setValueForOps(@NonNull String key, @NonNull Object value, @NonNull long timeout) {
        try {
            Objects.requireNonNull(key);
            Objects.requireNonNull(value);
            Objects.requireNonNull(timeout);
            redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * VALUE 获取值
     *
     * @param key REDIS-KEY
     * @return REDIS-VALUE
     */
    public Object getValueForOps(@NonNull String key) {
        try {
            Objects.requireNonNull(key);
            return redisTemplate.opsForValue().get(key);
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 删除KEY
     *
     * @param keySessionId REDIS-KEY
     * @return true 删除成功； false 删除失败
     */
    public boolean deleteValueForOps(@NonNull String keySessionId) {
        try {
            Objects.requireNonNull(keySessionId);
            return redisTemplate.delete(keySessionId);
        } catch (Exception ex) {
            return false;
        }
    }

    public Set<String> keys(@NonNull String sessionIdAll) {
        Objects.requireNonNull(sessionIdAll);
        return redisTemplate.keys(sessionIdAll);
    }

}
