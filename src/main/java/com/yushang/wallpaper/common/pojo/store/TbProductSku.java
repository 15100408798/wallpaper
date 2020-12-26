package com.yushang.wallpaper.common.pojo.store;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 商品sku
 */
@Setter
@Getter
@NoArgsConstructor
public class TbProductSku implements Serializable {

    private static final long serialVersionUID = -4859787699512647162L;
    /**
     * 商品sku表id
     */
    private Integer productSkuId;
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
     * 商品sku状态: 0.上架 1.下架
     */
    private Byte productSkuStatus;
    /**
     * 商品sku删除状态：0.未删除 1.已删除
     */
    private Byte deleteFlag;
    /**
     * 商品类别名称
     */
    private String productLabelName;

}
