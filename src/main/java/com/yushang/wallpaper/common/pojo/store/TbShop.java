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
 * 商户类
 */
@Entity(name = "tb_shop")
@Setter
@Getter
@NoArgsConstructor
public class TbShop implements Serializable {

    private static final long serialVersionUID = 5222950589519733692L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "int(32) comment '商家id'")
    private Long shopId;        // 商户id

    @Column(nullable = false, columnDefinition = "bigint(32) comment '用户id'")
    private Long userId;        // 用户id

    @Column(nullable = false, columnDefinition = "varchar(500) comment '商标'")
    private String shopLogo;    // 商标

    @Column(nullable = false, columnDefinition = "varchar(500) comment '商店名称'")
    private String shopName;    // 商店名称

    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm", timezone = "GTM+8")
    @Column(nullable = false, columnDefinition = "timestamp(6) comment '店铺上班时间'")
    private Date workTimeStart;     // 店铺上班时间

    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm", timezone = "GTM+8")
    @Column(nullable = false, columnDefinition = "timestamp(6) comment '停止营业时间'")
    private Date workTimeEnd;       // 停止营业时间

    @Column(nullable = false, columnDefinition = "int(32) comment '省份id'")
    private Integer provinceId;        // 省份id

    @Column(nullable = false, columnDefinition = "int(32) comment '城市id'")
    private Integer cityId;            // 城市id

    @Column(nullable = false, columnDefinition = "int(32) comment '县区id'")
    private Integer areaId;            // 县区id

    @Column(nullable = false, columnDefinition = "varchar(500) comment '地址'")
    private String address;             // 地址

    @Column(nullable = false, columnDefinition = "varchar(500) comment '商户介绍'")
    private String detail;              // 商户介绍

    @Column(nullable = false, columnDefinition = "varchar(100) comment '经营范围'")
    private String shopScope;           // 经营范围

    @Column(nullable = false, columnDefinition = "tinyint(1) comment '商户类型，1-壁纸，2-装修'")
    private Byte shopType;              // 商户类型，1-壁纸，2-装修

    private transient String tagName1;// 标签1

    private transient String tagName2;// 标签2

    private transient Double perMoney;// 人均消费

    @Column(nullable = false, columnDefinition = "tinyint(1) default 1 comment '上下架状态：1-上架，2-下架'")
    private Byte status;                // 上下架状态：1-上架，2-下架

    @Column(nullable = false, columnDefinition = "varchar(100) comment '纬度'")
    private String lat;         // 纬度

    @Column(nullable = false, columnDefinition = "varchar(100) comment '经度'")
    private String lng;         // 经度

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    @Column(nullable = false, columnDefinition = "timestamp(6)  comment '创建时间'")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    @Column(nullable = true, columnDefinition = "timestamp(6) comment '最后更新时间'")
    private Date LastUpdateTime;    // 最后更新时间

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0 comment '删除状态：0-未删除，1-已删除'")
    private Byte deleteFlag;        // 删除状态：0-未删除，1-已删除

    private transient String userPhone;        // 用户手机号
    private transient String userNick;        // 联系人昵称
    private transient String username;        // 联系人账号


}
