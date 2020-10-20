package com.yushang.wallpaper.common.config.Shiro.listener;

import com.yushang.wallpaper.common.config.Shiro.cache.RedisOperate;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

public class DefinedSessionListener implements SessionListener {

    private RedisOperate redisOperate;

    public void setRedisOperate(RedisOperate redisOperate){
        this.redisOperate = redisOperate;
    }

    public RedisOperate getRedisOperate(){
        return this.redisOperate;
    }

    /**
     * session开始
     */
    @Override
    public void onStart(Session session) {
        System.out.println("session开始了");
    }

    /**
     * session暂停
     */
    @Override
    public void onStop(Session session) {
        System.out.println("session暂停");
    }

    /**
     * 销毁session
     */
    @Override
    public void onExpiration(Session session) {
        getRedisOperate().delete(session.getId());
    }
}
