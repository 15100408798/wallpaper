package com.yushang.wallpaper.common.enums;

import lombok.Getter;

/**
 * 反馈状态枚举值
 *
 * @author machao
 * @version 1.0
 * @date 2020/12/9 0:42
 * @Description TODO
 */
@Getter
public enum FeekbackStatusEnum {

    FEEKBACK_STATUS_NO(1, "待处理"),
    FEEKBACK_STATUS_YES(2, "已处理"),
    FEEKBACK_STATUS_IGNORE(3, "已忽略"),

    ;

    private final int code;
    private final String message;

    FeekbackStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

}
