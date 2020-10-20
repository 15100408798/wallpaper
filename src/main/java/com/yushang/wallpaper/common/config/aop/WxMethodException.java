package com.yushang.wallpaper.common.config.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

import com.yushang.wallpaper.common.utils.log.LoggerUtils;

@Aspect
@Configuration
public class WxMethodException {
	
	private ThreadLocal<String> methodNameLocal = new ThreadLocal<>();
	
	@Pointcut(value="@annotation(com.yushang.wallpaper.common.config.aop.WxAop)")
	public void wxAop(){}
	
	@Before(value="wxAop()")
	public void wxBefore(JoinPoint joinPoint){
		//获得方法名
		String methodName = 
				joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName();
		//绑定线程
		methodNameLocal.set(methodName);
	}
	
	@After(value="wxAop()")
	public void wxAfter(JoinPoint joinPoint){}
	
	@AfterReturning(pointcut="wxAop()",returning="ret")
	public void wxAfterReturning(Object ret){
		//移除线程
		methodNameLocal.remove();
	}
	
	@AfterThrowing(pointcut="wxAop()",throwing="e")
	public void wxThrowing(Exception e){
		String methodName = methodNameLocal.get();
		LoggerUtils.error("发生异常,位置在"+methodName, e);
		//移除线程
		methodNameLocal.remove();
	}

}
