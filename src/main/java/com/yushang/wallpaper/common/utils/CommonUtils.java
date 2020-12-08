package com.yushang.wallpaper.common.utils;

import org.apache.commons.lang.RandomStringUtils;

public class CommonUtils {

    /**
     * 获取字母和数字结合的16位随机数
     *
     * @return 日志标识
     */
    public static String makeSessionId() {
        return RandomStringUtils.random(16, true, true);
    }

    public static String makeUserToken() {
        return RandomStringUtils.random(32, true, true);
    }


}
