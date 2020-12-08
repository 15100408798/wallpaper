package com.yushang.wallpaper.common.config.aop.wx;

import com.alibaba.fastjson.JSONObject;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.config.rabbitmq.SendMessage.SendMessageService;
import com.yushang.wallpaper.common.pojo.log.TbLog;
import com.yushang.wallpaper.common.utils.CommonUtils;
import com.yushang.wallpaper.common.utils.log.LoggerUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Configuration
public class WxLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(WxLogAspect.class);

    @Autowired
    private SendMessageService sendMessageService;

    private ThreadLocal<LogThreadLocal> methodNameThread = new ThreadLocal<LogThreadLocal>();

    @Pointcut(value = "@annotation(com.yushang.wallpaper.common.config.aop.wx.WxLog)")
    public void log() {
    }

    /**
     * 进入方法前，触发切面日志
     *
     * @param joinPoint 切面
     */
    @Before(value = "log()")
    public void before(JoinPoint joinPoint) {
        try {
            // 执行的业务方法名
            String methodName = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
            String sessionId = CommonUtils.makeSessionId();
            methodNameThread.set(new LogThreadLocal(methodName, sessionId));  // 绑定线程
            /* 记录请求日志 */
            WxLog wxLog = getAnnotation(joinPoint);   // 获得注解上的参数
            Object[] valueParam = joinPoint.getArgs();  // 请求参数
            String requestParam = getParameterNames(joinPoint, valueParam);
            LOGGER.info("{}|{}|{}|{}", LoggerUtils.REQUEST, wxLog.logEnum(), sessionId, requestParam);
//            String token = queryModel.getToken();
//            if (StringUtils.isBlank(token)) {
//                throw new RuntimeException("token不能为空");
//            }
        } catch (Exception e) {
            LoggerUtils.error("调用方法前，发生异常。位置在" + methodNameThread.get(), e);
        }
    }

    private WxLog getAnnotation(JoinPoint joinPoint) {
        // ( signature是信号,标识的意思 ):获取被增强的方法相关信息
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        // 获得方法信息
        Method method = methodSignature.getMethod();
        if (method != null)
            return method.getAnnotation(WxLog.class);
        return null;
    }

    /**
     * 获取请求参数
     *
     * @param joinPoint
     * @param valueParam 请求参数值
     * @return
     */
    private String getParameterNames(JoinPoint joinPoint, Object[] valueParam) {
        // 创建一个StringBuilder的对象
        StringBuffer stringBuffer = new StringBuffer();
        // 获得请求参数名称
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        String[] parameterNames = codeSignature.getParameterNames();    // 请求参数名称
        stringBuffer.append(ArrayUtils.isNotEmpty(parameterNames) ? JSONObject.toJSON(valueParam[0]) : "{}");
        return stringBuffer.toString();
    }

    @After(value = "log()")
    public void afterOperate(JoinPoint joinPoint) {

    }

    /**
     * 返回报文之前，执行该方法
     *
     * @param joinPoint 切面
     * @param ret       返回参数
     */
    @AfterReturning(pointcut = "log()", returning = "ret")
    public void afterOperate(JoinPoint joinPoint, Object ret) {
        try {
            // 获得请求request
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = servletRequestAttributes.getRequest();
            LogThreadLocal logThreadLocal = methodNameThread.get();
            String methodName = logThreadLocal.getMethodName(); // 执行的业务方法名
            String sessionId = logThreadLocal.getSessionId();   // 日志标识
            /* 从request中得到请求信息 */
            String requestURI = request.getRequestURI(); // 请求url地址
            Object[] valueParam = joinPoint.getArgs();  // 请求参数
            String requestParam = getParameterNames(joinPoint, valueParam);
            String method = request.getMethod();   // 获得请求类型  GET/POST
            String remoteAddr = request.getRemoteAddr();   // 获得ip地址
            WxLog wxLog = getAnnotation(joinPoint);   // 获得注解上的参数
            LOGGER.info("{}|{}|{}|{}", LoggerUtils.RESPONSE, wxLog.logEnum(), sessionId, JSONObject.toJSONString(ret));
            /* 记录日志 */
            Short managerId = (Short) SecurityUtils.getSubject().getPrincipal();
            if (managerId != null && wxLog.operateType() != 1) {
                TbLog tbLog = new TbLog();
                tbLog.setCreateTime(new Date());
                tbLog.setManagerIp(remoteAddr);
                tbLog.setOperateId(managerId);
                tbLog.setType("GET".equalsIgnoreCase(method) == true ? (byte) 1 : (byte) 2);
                tbLog.setRequestUri(requestURI);
                tbLog.setRequestParam(requestParam);
                tbLog.setMethodName(methodName);
                tbLog.setIsSuccess((byte) 1);
                tbLog.setTitle(wxLog.title());
                tbLog.setTabName(wxLog.tabName());
                tbLog.setOperateType(wxLog.operateType());
                tbLog.setSessionId(sessionId);
                //保存到数据库
                sendMessageService.saveLog(tbLog);
            }
        } catch (Exception e) {
            LoggerUtils.error("调用方法后，返回信息前，发生异常。位置在" + methodNameThread.get(), e);
        }
    }

    @AfterThrowing(pointcut = "log()", throwing = "e")
    public void throwsException(JoinPoint joinPoint, Exception e) {
        try {
            WxLog wxLog = getAnnotation(joinPoint);   // 获得注解上的参数
            Object[] valueParam = joinPoint.getArgs();  // 请求参数
            String requestParam = getParameterNames(joinPoint, valueParam);
            // 获取线程绑定的信息
            LogThreadLocal logThreadLocal = methodNameThread.get();
            String methodName = logThreadLocal.getMethodName();
            String sessionId = logThreadLocal.getSessionId();
            //将异常写入日志
            LOGGER.error("{}|{}|{}|{}|{}", LoggerUtils.EXCEPTION, wxLog.logEnum(), sessionId, requestParam, e);
        } finally {
            // 解除线程绑定的信息
            methodNameThread.remove();
        }
    }

    @Setter
    @Getter
    @NoArgsConstructor
    private class LogThreadLocal implements Serializable {
        private static final long serialVersionUID = -711545190034290470L;
        private String methodName;  // 方法名称
        private String sessionId;   // 日志标识

        public LogThreadLocal(String methodName, String sessionId) {
            this.methodName = methodName;
            this.sessionId = sessionId;
        }
    }

}
