package com.yushang.wallpaper.common.config.exception;

import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 拦截异常并统一处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {



    /**
     * 默认异常处理方法,返回异常请求路径和异常信息
     */
    @ExceptionHandler
    public String directErrorHtml(Exception e){
        return "common/error";
    }

}
