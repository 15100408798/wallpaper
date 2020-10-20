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
 * 商品信息类
 */
@Setter
@Getter
@NoArgsConstructor
@Entity(name = "tb_product")
public class TbProduct implements Serializable {

    private static final long serialVersionUID = -339930085896037326L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "int(32) comment '商品id'")
    private Integer productId;      // 商品id

    @Column(nullable = false, columnDefinition = "int(32) comment '商铺id'")
    private Integer shopId;         // 商铺id

    @Column(nullable = false, columnDefinition = "varchar(100) comment '商品名称'")
    private String productName;    // 商品名称

    @Column(nullable = false, columnDefinition = "varchar(500) comment '商品封面图片'")
    private String productImg;      // 商品封面图片

    @Column(nullable = false, columnDefinition = "varchar(800) comment '产品描述html'")
    private String productContent;         // 产品描述html

    @Column(nullable = false, columnDefinition = "tinyint(1) comment '商品类型：1-壁纸，2-墙画'")
    private Byte productType;      // 商品类型：1-壁纸，2-墙画

    @Column(nullable = false, columnDefinition = "varchar(800) comment '产品描述html'")
    private Byte xcType;            // 详细分类

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0 comment '是否推荐：0-否，1-首页推荐'")
    private Byte homeTopFlag;   // 是否推荐：0-否，1-首页推荐。  推荐优先展示

    private String sku_name;    //sku名称   (颜色、尺寸)

    private Short productLabelId;   // 产品子分类id

    @Column(nullable = false, columnDefinition = "tinyint(1) default 1 comment '上架状态：0-已上架， 1-已下架'")
    private Byte productStatus;    // 上下架状态：0-上架，1-下架

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    @Column(nullable = false, columnDefinition = "timestamp(6)  comment '创建时间'")
    private Date createTime;    // 创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    @Column(nullable = true, columnDefinition = "timestamp(6) comment '最后更新时间'")
    private Date lastUpdateTime;    // 最后更新时间

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0 comment '是否删除：0-未删除， 1-已删除。删除后不展示'")
    private Byte deleteFlag;    // 是否删除：0-未删除， 1-已删除。删除后不展示

    @Column(nullable = true, columnDefinition = "varchar(800) comment '商品详情图'")
    private String photo;      //商品详情图

    private transient String shopName;    // 商铺名称

}
