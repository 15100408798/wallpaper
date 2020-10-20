package com.yushang.wallpaper.common.config.Shiro.cache;

import com.yushang.wallpaper.common.utils.log.LoggerUtils;
import com.yushang.wallpaper.common.utils.redis.RedisUtils;
import org.apache.shiro.session.Session;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RedisOperate {

    private static final String sessionId = "wallpaper_session:";

    private static final String sessionIdAll = "wallpaper_session:*";

    private static final String kickoutId = "kickout_session:";

    @Resource
    private RedisUtils redisUtils;

    /**
     * 保存一个session
     */
    public void save(Serializable sessionId, Session session) {
        try {
            String keySessionId = getSessionId(sessionId);
            redisUtils.setValueForOps(keySessionId, session, 1800);
        } catch (Exception e) {
            LoggerUtils.error("保存一个session时，发生异常", e);
        }
    }

    /**
     * 通过id获得存储在redis中的值
     */
    public Object get(Serializable sessionId) {
        try {
            String keySessionId = getSessionId(sessionId);
            return redisUtils.getValueForOps(keySessionId);
        } catch (Exception e) {
            LoggerUtils.error("通过id获得存储在redis中的值时，发生异常", e);
        }
        return null;
    }

    /**
     * 删除session
     */
    public void delete(Serializable id) {
        try {
            String keySessionId = getSessionId(sessionId);
            redisUtils.deleteValueForOps(keySessionId);
        } catch (Exception e) {
            LoggerUtils.error("删除session时，发生异常", e);
        }
    }

    /**
     * 获取所有存活的session
     */
    public Collection<Session> getActiveSessions() {
        Set<Session> sessionSet = new HashSet<>();
        try {
            Set<String> keys = redisUtils.keys(sessionIdAll);
            if (keys != null && keys.size() > 0) {
                Iterator<String> iterator = keys.iterator();
                if (iterator.hasNext()) {
                    Session sesion = (Session) redisUtils.getValueForOps(iterator.next());
                    sessionSet.add(sesion);
                }
            }
            return sessionSet;
        } catch (Exception e) {
            LoggerUtils.error("获取所有存活的session时，发生异常", e);
        }
        return null;
    }


    private String getSessionId(Serializable id) {
        return sessionId + id;
    }

    private String getKickoutId(Serializable id) {
        return kickoutId + id;
    }


    /**
     * 踢出，获得用户信息
     */
    public Object kickoutGetSession(Short managerId) {
        try {
            String kickoutId = getKickoutId(managerId);
            return redisUtils.getValueForOps(kickoutId);
        } catch (Exception e) {
            LoggerUtils.error("踢出，获得用户信息时，发生异常", e);
        }
        return null;
    }


    /**
     * 踢出，保存用户信息
     */
    public void kickoutSetSession(Short managerId, Serializable id) {
        try {
            String kickoutId = getKickoutId(managerId);
            redisUtils.setValueForOps(kickoutId, id, 3600);
        } catch (Exception e) {
            LoggerUtils.error("踢出，保存用户信息时，发生异常", e);
        }
    }
}
