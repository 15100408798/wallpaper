package com.yushang.wallpaper.common.config.Shiro.dao;

import com.yushang.wallpaper.common.config.Shiro.cache.RedisOperate;
import com.yushang.wallpaper.common.utils.log.LoggerUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import java.io.Serializable;
import java.util.Collection;

public class DefinedSessionDao extends AbstractSessionDAO {

    private RedisOperate redisOperate;

    public void setRedisOperate(RedisOperate redisOperate){
        this.redisOperate = redisOperate;
    }

    public RedisOperate getRedisOperate(){
        return this.redisOperate;
    }

    /**
     * 创建session
     * @param session
     * @return
     */
    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        getRedisOperate().save(sessionId,session);
        return sessionId;
    }

    /**
     * 读取session
     * @param sessionId
     * @return
     */
    @Override
    protected Session doReadSession(Serializable sessionId) {
       Object object =  getRedisOperate().get(sessionId);
       if (object == null){
           return null;
       }
       //判断一下是不是session类型
       if (object instanceof Session)
           return (Session)object;
       return null;
    }

    /**
     * 更新session
     * @param session
     * @throws UnknownSessionException
     */
    @Override
    public void update(Session session) throws UnknownSessionException {
        getRedisOperate().save(session.getId(),session);
    }

    /**
     * 删除session
     * @param session
     */
    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null){
            LoggerUtils.error("session为空，或者sessionId为空");
        }
        getRedisOperate().delete(session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {
        return getRedisOperate().getActiveSessions();
    }
}
