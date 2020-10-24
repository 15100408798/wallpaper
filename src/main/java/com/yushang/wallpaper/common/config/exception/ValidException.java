package com.yushang.wallpaper.common.config.exception;

import com.yushang.wallpaper.common.config.enums.ValidEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class ValidException extends Exception {

    private static final long serialVersionUID = -4400831708165689391L;
    private int status;     // 状态码
    private String message;     // 提示信息

    public ValidException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public ValidException(@NonNull ValidEnum validEnum) {
        Objects.requireNonNull(validEnum);
        this.status = validEnum.getCode();
        this.message = validEnum.getMessage();
    }

}
