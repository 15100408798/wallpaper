package com.yushang.wallpaper.common.config.aop.shiro;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解的使用范围，运行时间
 */
@Documented
@Target(value={ElementType.TYPE,ElementType.METHOD})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface PermissionName {
	
	String value() default "";

}
