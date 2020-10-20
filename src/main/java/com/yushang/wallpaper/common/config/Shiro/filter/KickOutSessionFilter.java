package com.yushang.wallpaper.common.config.Shiro.filter;

import com.yushang.wallpaper.common.config.Shiro.cache.RedisOperate;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

public class KickOutSessionFilter extends AccessControlFilter {

    //在线用户
    private final static String ONLINE_STATUS = "user_is_online";

    //提出状态,true表示踢出
    private final static String KICKOUT_STATUS = "user_status";

    private RedisOperate redisOperate;

    public void setRedisOperate(RedisOperate redisOperate) {
        this.redisOperate = redisOperate;
    }

    public RedisOperate getRedisOperate() {
        return this.redisOperate;
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //得到请求路径
        String requestURI = request.getRequestURI();
        //得到登陆的主体
        Subject subject = getSubject(servletRequest, servletResponse);

        Session session = subject.getSession();
        //得到登陆者的sessionid
        Serializable id = session.getId();

        /**
         * 判断是否已经踢出
         */
        Boolean marker = (Boolean) session.getAttribute(KICKOUT_STATUS);
        if (marker != null && marker) {
            return Boolean.FALSE;
        }

        //得到主体
        Short managerId = (Short) subject.getPrincipal();
        //从缓存获取获取用户session信息
        Serializable serRedisId = (Serializable) getRedisOperate().kickoutGetSession(managerId);
        if (serRedisId == null) {
            //存储到缓存1个小时（这个时间最好和session的有效期一致或者大于session的有效期）
            getRedisOperate().kickoutSetSession(managerId, id);
            return Boolean.TRUE;
        }
        Map<Short, Serializable> infoMap = new LinkedHashMap<>();
        infoMap.put(managerId, serRedisId);
        //如果已经包含当前session，并且是同一个用户，跳过
        if (infoMap.containsKey(managerId) && infoMap.containsValue(id)) {
            //存储到缓存1个小时（这个时间最好和session的有效期一致或者大于session的有效期）
            getRedisOperate().kickoutSetSession(managerId, id);
            return Boolean.TRUE;
        }

        /**
         * 如果用户相同，session不同，那就要处理了
         */

        /**
         * 如果用户id相同，session不同
         * 1、获取到原来的session，并标记为踢出
         * 2、继续走
         */
        if (infoMap.containsKey(managerId) && !infoMap.containsValue(id)) {
            Serializable serializableId = infoMap.get(managerId);
            Session oldSession = (Session) getRedisOperate().get(serializableId);
            if (oldSession != null) {
                //把session踢出
                oldSession.setAttribute(KICKOUT_STATUS, Boolean.TRUE);
                //更新session
                getRedisOperate().save(serializableId, oldSession);
                //存储到缓存1个小时（这个时间最好和session的有效期一致或者大于session的有效期）
                getRedisOperate().kickoutSetSession(managerId, id);
            } else {
                getRedisOperate().delete(serializableId);
                //存储到缓存1个小时（这个时间最好和session的有效期一致或者大于session的有效期）
                getRedisOperate().kickoutSetSession(managerId, id);
            }
            return Boolean.TRUE;
        }

        if (!infoMap.containsKey(managerId) && !infoMap.containsKey(id)) {
            //存储到缓存1个小时（这个时间最好和session的有效期一致或者大于session的有效期）
            getRedisOperate().kickoutSetSession(managerId, id);
        }
        return Boolean.TRUE;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //先退出
        Subject subject = getSubject(servletRequest, servletResponse);
        subject.logout();
        //保存之前访问的路径
        WebUtils.getSavedRequest(servletRequest);
        //再重定向
        WebUtils.issueRedirect(servletRequest, servletResponse, "/skip/common/login?kickout");
        return false;
    }
}
