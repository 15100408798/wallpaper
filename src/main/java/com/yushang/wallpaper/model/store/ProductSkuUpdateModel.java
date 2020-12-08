package com.yushang.wallpaper.model.store;

import com.yushang.wallpaper.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

/**
 * 商品sku -- Product Sku Update Model
 */
@Setter
@Getter
@NoArgsConstructor
public class ProductSkuUpdateModel extends BaseModel {

    private static final long serialVersionUID = 3286082462301517997L;
    private String productSkuIds;   // 商品skuId
    private String[] productSkuIdValues;   // 商品skuId数组
    private Integer deleteFlag = 0; // 删除状态：0-未删除，1-已删除
    private Integer productSkuStatus; // 上下架状态：0-上架，1-下架
    private Date lastUpdateTime = new Date();    // 最后更新时间

    public ProductSkuUpdateModel(int page, int size) {
        super(page, size);
    }

    public void validProuductIdsNonNull() {
        Objects.requireNonNull(productSkuIds);
    }

}
