package com.yushang.wallpaper.layer.model.user;

import com.yushang.wallpaper.layer.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 客服模块 -- Custom Query Model
 */
@Setter
@Getter
@NoArgsConstructor
public class CustomQueryModel extends BaseModel {

    private static final long serialVersionUID = 4862557322974551382L;
    private Integer deleteFlag;  // 删除状态：0-未删除，1-已删除

    public CustomQueryModel(int page, int size) {
        super(page, size);
    }


}
