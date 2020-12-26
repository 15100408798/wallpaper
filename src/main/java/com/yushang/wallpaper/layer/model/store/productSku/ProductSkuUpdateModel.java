package com.yushang.wallpaper.layer.model.store.productSku;

import com.yushang.wallpaper.layer.model.base.BaseModel;
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

    private static final long serialVersionUID = -3886502077778155452L;
    /**
     * 商品skuId
     */
    private String productSkuIds;
    /**
     * 商品skuId数组
     */
    private String[] productSkuIdValues;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Integer deleteFlag = 0;
    /**
     * 上下架状态：0-上架，1-下架
     */
    private Integer productSkuStatus;
    /**
     * 最后更新时间
     */
    private Date lastUpdateTime = new Date();
    /**
     * 商品类别ID
     */
    private Integer productLabelId;
    /**
     * 商品sku颜色/类别  （戴尔笔记本电脑灵越5600版, 颜色：白色）
     */
    private String productSkuName;
    /**
     * 商品sku尺寸   ( 尺寸：24寸)
     */
    private String productSkuSize;

    public ProductSkuUpdateModel(int page, int size) {
        super(page, size);
    }

    public void validProuductIdsNonNull() {
        Objects.requireNonNull(productSkuIds);
    }

}
