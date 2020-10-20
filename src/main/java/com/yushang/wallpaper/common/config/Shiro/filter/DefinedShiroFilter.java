package com.yushang.wallpaper.common.config.Shiro.filter;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.yushang.wallpaper.common.config.Shiro.cache.RedisOperate;
import com.yushang.wallpaper.common.config.Shiro.dao.DefinedSessionDao;
import com.yushang.wallpaper.common.config.Shiro.listener.DefinedSessionListener;
import com.yushang.wallpaper.common.config.Shiro.realm.DefinedRealm;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.eis.JavaUuidSessionIdGenerator;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.*;

@Configuration
public class DefinedShiroFilter {

    /**
     * 1、定义过滤器
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(){
        //1、创建过滤器工厂
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //2、定义安全管理器
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //3、定义登陆url
        shiroFilterFactoryBean.setLoginUrl("/skip/common/login");
        //4、定义登陆成功后跳转的url
        shiroFilterFactoryBean.setSuccessUrl("/skip/common/index");
        //5、定义无权限跳转的url
        shiroFilterFactoryBean.setUnauthorizedUrl("/skip/common/unauthorized");
        //6、定义过滤器链条
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("login",new LoginFilter());
        filterMap.put("kickout",kickOutSessionFilter());
        shiroFilterFactoryBean.setFilters(filterMap);
        //7、定义地址权限
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/css/**","anon");
        map.put("/fonts/**","anon");
        map.put("/images/**","anon");
        map.put("/js/**","anon");
		map.put("/swagger/**","anon");
        map.put("/skip/common/login","anon");
        map.put("/layer/user/submitLogin","anon");
        map.put("/layer/user/logout","logout");
        map.put("/layer/**","login,kickout");
        map.put("/skip/**","login,kickout");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        //8、返回结果
        return shiroFilterFactoryBean;
    }

    private Filter kickOutSessionFilter() {
        KickOutSessionFilter kickOutSessionFilter = new KickOutSessionFilter();
        kickOutSessionFilter.setRedisOperate(redisOperate());
        return kickOutSessionFilter;
    }

    /**
     * 2、定义安全管理器
     */
    @Bean
    public SecurityManager securityManager() {
        //1、定义默认的安全管理器
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //2、定义realm
        defaultWebSecurityManager.setRealm(getDefinedRealm());
        //3、定义rememberMe
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());
        //4、定义sessionManager
        defaultWebSecurityManager.setSessionManager(sessionManager());
        //5、返回信息
        return defaultWebSecurityManager;
    }

    @Bean
    public  Realm getDefinedRealm() {
        DefinedRealm definedRealm = new DefinedRealm();
        definedRealm.setCredentialsMatcher(getCredentialsMatcher());
        // 关闭cache; 否则一直报错：No cache or cacheManager properties have been set. Authorization cache cannot be obtained.
        definedRealm.setAuthenticationCachingEnabled(false);
        return definedRealm;
    }

    @Bean
    public  CredentialsMatcher getCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(3);
        return hashedCredentialsMatcher;
    }

    /**
     * 创建rememberMe对象
     */
    @Bean
    public  RememberMeManager rememberMeManager() {
        //1、创建对象
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        //2、设置cookie对象
        cookieRememberMeManager.setCookie(simpleCookie());
        //3、返回结果
        return cookieRememberMeManager;
    }

    /**
     * 创建cookie
     */
    @Bean
    public  Cookie simpleCookie() {
        //1、创建对象
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        //2、设置时间、安全性（防止攻击）
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(84600);
        //3、返回信息
        return simpleCookie;
    }

    /**
     * 创建sessionManager对象
     */
    @Bean
    public  SessionManager sessionManager() {
        //1、创建sessionManager对象
        DefaultWebSessionManager defaultWebSessionManager = new DefaultWebSessionManager();
        //2、设置信息
        //自动删除过期的session
        defaultWebSessionManager.setDeleteInvalidSessions(true);
        //自动检查sessionManager
        defaultWebSessionManager.setSessionValidationSchedulerEnabled(true);
        //检查设置
        defaultWebSessionManager.setSessionValidationScheduler(sessionValidationScheduler());
        //全局session时间
        defaultWebSessionManager.setGlobalSessionTimeout(1800000);
        //session失效时间
        defaultWebSessionManager.setSessionValidationInterval(1800000);
        //自定义sessionDao
        defaultWebSessionManager.setSessionDAO(sessionDao());
        //会话Cookie模板
        defaultWebSessionManager.setSessionIdCookie(sessionCookie());
        //监听器
        defaultWebSessionManager.setSessionListeners(getListeners());
        //3、返回信息
        return defaultWebSessionManager;
    }

    @Bean
    public  Collection<SessionListener> getListeners() {
        Collection<SessionListener> list = new ArrayList<>();
        DefinedSessionListener definedSessionListener = new DefinedSessionListener();
        definedSessionListener.setRedisOperate(redisOperate());
        list.add(definedSessionListener);
        return list;
    }

    @Bean
    public RedisOperate redisOperate() {
        //redis操作
        return  new RedisOperate();
    }

    @Bean
    public  Cookie sessionCookie() {
        //1、创建对象
        SimpleCookie simpleCookie = new SimpleCookie("sessionIdCookie");
        //2、设置时间、安全性（防止攻击）
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(1800);
        //3、返回信息
        return simpleCookie;
    }

    @Bean
    public  SessionDAO sessionDao() {
        DefinedSessionDao definedSessionDao = new DefinedSessionDao();
        definedSessionDao.setRedisOperate(redisOperate());
        definedSessionDao.setSessionIdGenerator(new JavaUuidSessionIdGenerator());
        return definedSessionDao;
    }


    @Bean
    public  SessionValidationScheduler sessionValidationScheduler() {
        //检查session定时器
        ExecutorServiceSessionValidationScheduler executorServiceSessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
        executorServiceSessionValidationScheduler.setInterval(1800000);
        return executorServiceSessionValidationScheduler;
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }


    /**
     * 开启spring  aop注解，使用代理方式，所以需要开启代码支持
     *  一定要写入上面advisorAutoProxyCreator（）自动代理。不然AOP注解不会生效
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 加上这个shiro标签才会起作用
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }


}
