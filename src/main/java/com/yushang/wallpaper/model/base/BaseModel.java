package com.yushang.wallpaper.model.base;

import com.github.pagehelper.PageHelper;
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
    private Integer page;   // 当前页码
    private Integer size;   // 每页条数

    public BaseModel(Integer page, Integer size) {
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

    /**
     * 分页
     */
    public void startPage() {
        if (page != null && size != null) {
            PageHelper.startPage(page, size);
        }
    }

}
