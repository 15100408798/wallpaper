package com.yushang.wallpaper.layer.model.store.product;

import com.yushang.wallpaper.layer.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

/**
 * 商品管理 -- Product Insert Model
 */
@Setter
@Getter
@NoArgsConstructor
public class ProductInsertModel extends BaseModel {

    private static final long serialVersionUID = 7640636338842538596L;
    /**
     * 商铺id
     */
    private Integer shopId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品封面图片
     */
    private String productImg;
    /**
     * 产品描述html
     */
    private String productContent;
    /**
     * 商品类型：1-壁纸，2-墙画
     */
    private Integer productType;
    /**
     * 商品类别名称
     */
    private String productLabelName;
    /**
     * 详细分类
     */
    private Integer xcType;
    /**
     * 是否推荐,推荐优先展示：
     * 0-否，1-首页推荐。
     */
    private Integer homeTopFlag;
    /**
     * sku名称 (颜色、尺寸)
     */
    private String skuName;
    /**
     * 产品子分类id
     */
    private Integer productLabelId;
    /**
     * 上下架状态
     * 0-上架，1-下架
     */
    private Integer productStatus;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 最后更新时间
     */
    private Date lastUpdateTime;
    /**
     * 是否删除,删除后不展示
     * 0-未删除， 1-已删除
     */
    private Integer deleteFlag;
    /**
     * 商品详情图
     */
    private String photo;
    /**
     * 商铺名称
     */
    private String shopName;



}
