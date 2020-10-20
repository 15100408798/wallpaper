package com.yushang.wallpaper.layer.model.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@NoArgsConstructor
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 1024001238719705267L;
    private int page;   // 当前页码
    private int size;   // 每页条数

    public BaseModel(int page, int size) {
        this.page = page;
        this.size = size;
    }

    /**
     * 校验参数
     */
    public void validPageSizeIsNull() {
        Objects.requireNonNull(this.page);
        Objects.requireNonNull(this.size);
    }

}
