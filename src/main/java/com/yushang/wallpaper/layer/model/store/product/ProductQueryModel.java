package com.yushang.wallpaper.layer.model.store.product;

import com.yushang.wallpaper.layer.model.base.BaseModel;
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

    /**
     * 商品ID
     */
    private Integer productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Integer deleteFlag;
    /**
     * 商铺id
     */
    private Integer shopId;
    /**
     * 商品类型：1-壁纸，2-墙画
     */
    private Integer productType;
    /**
     * 是否推荐,推荐优先展示：
     * 0-否，1-首页推荐。
     */
    private Integer homeTopFlag;
    /**
     * 上下架状态
     * 0-上架，1-下架
     */
    private Byte productStatus;



}
