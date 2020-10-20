package com.yushang.wallpaper.layer.model.store;

import com.yushang.wallpaper.layer.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 商品sku -- Product Sku Query Model
 */
@Setter
@Getter
@NoArgsConstructor
public class ProductSkuQueryModel extends BaseModel {

    private static final long serialVersionUID = -7708136068916162767L;
    private String productName;     // 商品名称
    private Integer deleteFlag = 0; // 删除状态：0-未删除，1-已删除
    private Integer productSkuStatus; // 上下架状态：1-上架，2-下架

    public ProductSkuQueryModel(int page, int size) {
        super(page, size);
    }

}
