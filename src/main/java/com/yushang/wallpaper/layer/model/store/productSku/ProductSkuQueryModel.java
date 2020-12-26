package com.yushang.wallpaper.layer.model.store.productSku;

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

    private static final long serialVersionUID = -1528784573689018912L;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Integer deleteFlag;
    /**
     * 上下架状态：1-上架，2-下架
     */
    private Integer productSkuStatus;
    /**
     * 商品SkuID
     */
    private Integer productSkuId;
    /**
     * 不包含商品SkuID
     */
    private Integer notProductSkuId;
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

    public ProductSkuQueryModel(int page, int size) {
        super(page, size);
    }

}
