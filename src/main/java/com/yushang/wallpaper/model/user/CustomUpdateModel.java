package com.yushang.wallpaper.model.user;

import com.yushang.wallpaper.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

/**
 * 客服模块 -- Custom Update Model
 */
@Setter
@Getter
@NoArgsConstructor
public class CustomUpdateModel extends BaseModel {

    private static final long serialVersionUID = 3696300735164521351L;
    private String customIds;   // 客服ID
    private String[] customIdValues; // 客服ID集合
    private Date lastUpdateTime = new Date();
    private Integer deleteFlag;     // 删除状态：0-未删除，1-已删除

    public void validCustomIdNonNull() {
        Objects.requireNonNull(customIds);
    }

}
