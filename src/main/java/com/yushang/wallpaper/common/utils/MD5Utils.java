package com.yushang.wallpaper.common.utils;


import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.lang.NonNull;

import java.util.Objects;

public class MD5Utils {

    /**
     * MD5加密
     *
     * @param password 密码
     * @param salt     加密盐
     * @return 加密后的密码
     */
    public static String disrect(@NonNull String password, @NonNull String salt) {
        Objects.requireNonNull(password, "密码不能为null");
        Objects.requireNonNull(salt, "加密盐不能为Null");
        return new SimpleHash("MD5", password, salt, 3).toString();
    }


    public static void main(String[] args) {
        String password = disrect("123456", "xiaohang");
        System.out.println(password);
    }

}
