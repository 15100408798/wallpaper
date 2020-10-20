package com.yushang.wallpaper.common.pojo.store;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 商品sku
 */
@Setter
@Getter
@NoArgsConstructor
@Entity(name = "tb_product_sku")
public class TbProductSku implements Serializable {

    private static final long serialVersionUID = -4453257755634716402L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "int(32) comment '商品sku表id'")
    private Integer productSkuId;   //  商品sku表id

    @Column(nullable = false, columnDefinition = "int(32) comment '商品类别ID'")
    private Integer productLabelId;      // 商品类别ID

    @Column(nullable = false, columnDefinition = "varchar(100) comment '商品sku颜色/类别'")
    private String productSkuName;     // 商品sku颜色/类别  （戴尔笔记本电脑灵越5600版, 颜色：白色）

    @Column(nullable = false, columnDefinition = "varchar(100) comment '商品sku尺寸'")
    private String productSkuSize;      // 商品sku尺寸   ( 尺寸：24寸)

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    @Column(nullable = false, columnDefinition = "timestamp(0) default CURRENT_TIMESTAMP comment '创建时间'")
    private Date createTime;    // 创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    @Column(nullable = true, columnDefinition = "timestamp(0) default CURRENT_TIMESTAMP comment '最后更新时间'")
    private Date lastUpdateTime;    // 最后更新时间

    @Column(nullable = false, columnDefinition = "tinyint(1) default 1 comment '商品sku状态: 0.上架 1.下架'")
    private Byte productSkuStatus;          // 商品sku状态: 0.上架 1.下架

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0 comment '商品sku删除状态：0.未删除 1.已删除'")
    private Byte deleteFlag;                //  商品sku删除状态：0.未删除 1.已删除

    private transient String productLabelName;   // 商品类别名称

}
