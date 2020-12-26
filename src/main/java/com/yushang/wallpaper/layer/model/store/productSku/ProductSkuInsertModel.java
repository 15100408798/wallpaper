package com.yushang.wallpaper.layer.model.store.productSku;

import com.yushang.wallpaper.layer.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * 商品sku -- Product Sku Insert Model
 */
@Setter
@Getter
@NoArgsConstructor
public class ProductSkuInsertModel extends BaseModel {

    private static final long serialVersionUID = -7127001618196855150L;
    /**
     * 商品skuId
     */
    private String productSkuIds;
    /**
     * 商品skuId数组
     */
    private String[] productSkuIdValues;
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
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date lastUpdateTime;
    /**
     * 商品sku状态: 0.上架 1.下架
     */
    private Integer productSkuStatus;
    /**
     * 商品sku删除状态：0.未删除 1.已删除
     */
    private Integer deleteFlag;
    /**
     * 商品类别名称
     */
    private String productLabelName;

}
