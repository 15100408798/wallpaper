package com.yushang.wallpaper.common.utils.log;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;

/**
 * Log日志封装输出
 */
public class LoggerUtils {

    public static final Logger LOGGER = LoggerFactory.getLogger(LoggerUtils.class);
    public static final String REQUEST = "REQUEST";
    public static final String RESPONSE = "RESPONSE";
    public static final String EXCEPTION = "EXCEPTION";

    /**
     * Error 输出
     */
    public static void error(String message, Exception e) {
        LOGGER.error(message, e);
    }

    public static void error(String message) {
        error(message, null);
    }

    /**
     * Error 输出
     */
    public static void info(String message) {
        LOGGER.info(message);
    }

    /**
     * 记录请求参数
     *
     * @param logEnum      日志枚举值
     * @param sessionId    日志标识
     * @param requestParam 请求参数
     */
    public static void recordRequestLog(@NonNull String logEnum, @NonNull String sessionId, @NonNull Object requestParam) {
        LOGGER.info("{}|{}|{}|{}", REQUEST, logEnum, sessionId, JSONObject.toJSONString(requestParam));
    }

    /**
     * 记录响应参数
     *
     * @param logEnum   日志枚举值
     * @param sessionId 日志标识
     * @param response  响应报文
     */
    public static void recordResponseLog(@NonNull String logEnum, @NonNull String sessionId, @NonNull Object response) {
        LOGGER.info("{}|{}|{}|{}", RESPONSE, logEnum, sessionId, JSONObject.toJSONString(response));
    }

    /**
     * 记录异常参数
     *
     * @param logEnum      日志枚举值
     * @param sessionId    日志标识
     * @param requestParam 请求参数
     * @param ex           异常日志
     */
    public static void recordExceptionLog(@NonNull String logEnum, @NonNull String sessionId, @NonNull Object requestParam, @NonNull Exception ex) {
        LOGGER.error("{}|{}|{}|{}|{}", EXCEPTION, logEnum, sessionId, JSONObject.toJSONString(requestParam), ex);
    }

}
