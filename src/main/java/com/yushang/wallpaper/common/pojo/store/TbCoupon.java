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
 * 优惠券
 */
@Entity(name = "tb_coupon")
@Setter
@Getter
@NoArgsConstructor
public class TbCoupon implements Serializable {

    private static final long serialVersionUID = -6240844189557670147L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, columnDefinition = "int(32) comment '优惠劵Id'")
    private Integer couponId;   // 优惠券id

    @Column(nullable = false, columnDefinition = "int(32) comment '商铺id'")
    private Long shopId;        // 商铺id

    @Column(nullable = false, columnDefinition = "varchar(32) comment '优惠劵名称'")
    private String couponName;          //  优惠劵名称

    @Column(nullable = false, columnDefinition = "varchar(32) comment '开始时间'")
    private String startTime;           //  开始时间

    @Column(nullable = false, columnDefinition = "varchar(32) comment '结束时间'")
    private String endTime;             //  结束时间

    @Column(nullable = false, columnDefinition = "tinyint(1) default 1 comment '优惠券类型 (1满减，2打折)'")
    private Byte type;                  //  优惠券类型 (1-满减，2-打折)

    @Column(nullable = false, columnDefinition = "varchar(32) comment '根据类型拿值, 例:满减：100,5 满100减5块  9折：9'")
    private String typeValue;           //  根据类型拿值 , 例:满减：100,5 满100减5块  9折：9

    @Column(nullable = false, columnDefinition = "tinyint(1) default 1 comment '1-下单, 2-扫码'")
    private Byte useType;               //  1-下单, 2-扫码

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GTM+8")
    @Column(nullable = false, columnDefinition = "datetime(6) comment '创建时间'")
    private Date createTime;            //   创建时间

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0 comment '删除状态：0-未删除， 1-已删除'")
    private Byte deleteFlag;            //   删除状态：0-未删除， 1-已删除

    private transient String shopName;  // 商铺名称

}
