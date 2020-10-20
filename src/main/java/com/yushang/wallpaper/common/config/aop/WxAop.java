package com.yushang.wallpaper.common.config.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value=ElementType.METHOD)	//表示该注解使用范围
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface WxAop {

}
