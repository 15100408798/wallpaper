package com.yushang.wallpaper.common.config.Shiro.filter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class LoginFilter extends AccessControlFilter {

    /**
     * 允许访问
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object mapperValue) throws Exception {
        //mapperValue  一般是定义shiro.properties所带地址
        //1、得到主体
        Subject subject = getSubject(servletRequest, servletResponse);
        Short managerId   = (Short) subject.getPrincipal();
        //2、判断是否登陆
        if (managerId != null || isLoginRequest(servletRequest,servletResponse)){
            return Boolean.TRUE;
        }
        //3、返回信息
        return Boolean.FALSE;
    }

    /**
     * 拒接访问
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //1、记录下访问Url
        WebUtils.getSavedRequest(servletRequest);
        //再重定向
        WebUtils.issueRedirect(servletRequest, servletResponse,"/skip/common/login");
        //2、返回信息
        return Boolean.FALSE;
    }
}
