package com.yushang.wallpaper.model.store;

import com.yushang.wallpaper.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 商品管理 -- Product Query Model
 */
@Setter
@Getter
@NoArgsConstructor
public class ProductQueryModel extends BaseModel {
    private static final long serialVersionUID = 1255119910418586253L;

    private String productName;     // 商品名称
    private Integer deleteFlag = 0; // 删除状态：0-未删除，1-已删除

}
