package com.yushang.wallpaper.common.config.enums;

import lombok.Getter;

@Getter
public enum ValidEnum {

    NO_FOUND_INFO(10001, "信息不存在"),
    SELECT_INFO_ERROR(10002, "查询结果不符合预期"),

    ;

    private final int code;
    private final String message;

    ValidEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
