package com.yushang.wallpaper.common.pojo.store;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品信息类
 */
@Setter
@Getter
@NoArgsConstructor
public class TbProduct implements Serializable {

    private static final long serialVersionUID = 5401439247715039669L;
    /**
     * 商品id
     */
    private Integer productId;
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
    private Byte productType;
    /**
     * 商品类别名称
     */
    private String productLabelName;
    /**
     * 详细分类
     */
    private Byte xcType;
    /**
     * 是否推荐,推荐优先展示：
     * 0-否，1-首页推荐。
     */
    private Byte homeTopFlag;
    /**
     * sku名称 (颜色、尺寸)
     */
    private String skuName;
    /**
     * 产品子分类id
     */
    private Short productLabelId;
    /**
     * 上下架状态
     * 0-上架，1-下架
     */
    private Byte productStatus;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date createTime;
    /**
     * 最后更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    private Date lastUpdateTime;
    /**
     * 是否删除,删除后不展示
     * 0-未删除， 1-已删除
     */
    private Byte deleteFlag;
    /**
     * 商品详情图
     */
    private String photo;
    /**
     * 商铺名称
     */
    private String shopName;

}
