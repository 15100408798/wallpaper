package com.yushang.wallpaper.common.enums;

import lombok.Getter;

/**
 * @author machao
 * @version 1.0
 * @date 2020/12/9 0:30
 * @Description TODO
 */
@Getter
public enum StatusEnum {

    DELETE_NO(0, "未删除"),
    DELETE_YES(1, "已删除"),
    UP(0, "上架"),
    DOWN(1, "下架"),


    ;

    private final int code;
    private final String message;

    StatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
