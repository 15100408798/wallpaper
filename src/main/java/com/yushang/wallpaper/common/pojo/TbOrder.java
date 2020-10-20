package com.yushang.wallpaper.common.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "tb_order")
public class TbOrder implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,columnDefinition = "int(32) comment '订单表'")
    private Integer orderId;        //订单表

    @Column(nullable = false,columnDefinition = "varchar(32) comment '订单编号 RyyyyMMdd+随机4位+0000001'")
    private String orderNo;         //订单编号   RyyyyMMdd+随机4位+0000001

    @Column(nullable = false,columnDefinition = "tinyint(1) default 1 comment '1 未付款 2 待发货 3 待收货   4 待评价 5 已完成  6 已退款'")
    private Byte status;        //1 未付款 2 待发货 3 待收货   4 待评价 5 已完成  6 已退款

    @Column(nullable = false,columnDefinition = "decimal(15,2) default 0.00 comment '总金额'")
    private BigDecimal allMoney;        //总金额

    @Column(nullable = false,columnDefinition = "decimal(15,2) default 0.00 comment '优惠金额'")
    private BigDecimal minusMoney;      //优惠金额

    @Column(nullable = false,columnDefinition = "decimal(15,2) default 0.00 comment '实际付款金额'")
    private BigDecimal payMoney;        //实际付款金额

    @Column(nullable = false,columnDefinition = "smallint(6) default 0 comment '使用积分(100:1)'")
    private Short score;            //使用积分(100:1)

    @Column(nullable = false,columnDefinition = "tinyint(1) default 2 comment '支付方式  1微信2余额（全款）3积分折扣'")
    private Byte payType;           //支付方式  1微信2余额（全款）3积分折扣

    @Column(nullable = false,columnDefinition = "int(32)  comment '收获地址Id'")
    private Integer addressId;      //收获地址Id

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GTM+8")
    @Column(nullable = false,columnDefinition = "datetime(6) comment '创建时间'")
    private Date createTime;        //创建时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GTM+8")
    @Column(nullable = true,columnDefinition = "datetime(6) comment '付款时间'")
    private Date payTime;       //付款时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GTM+8")
    @Column(nullable = true,columnDefinition = "datetime(6) comment '发货时间'")
    private Date sendTime;      //发货时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GTM+8")
    @Column(nullable = true,columnDefinition = "datetime(6) comment '完成时间'")
    private Date completeTime;      //完成时间

    @Column(nullable = false,columnDefinition = "tinyint(1) default 0 comment '0  未删除    1  已删除'")
    private Byte isDel;         //  0  未删除    1  已删除

    @Column(nullable = false,columnDefinition = "int(32) comment '用户id'")
    private Integer  userId;    //用户id

    @Column(nullable = false,columnDefinition = "int(32) comment '商户id'")
    private Integer  shopId;       //商户id

    @Column(nullable = false,columnDefinition = "tinyint(32) comment '配送方式(1自提，2快递)'")
    private Byte sendType;      //配送方式(1自提，2快递)

    @Column(nullable = true,columnDefinition = "varchar(32) comment '物流公司'")
    private String logisticsName;       //物流公司

    @Column(nullable = true,columnDefinition = "varchar(32) comment '物流订单号'")
    private String logisticsNumber;         //物流订单号

    /** ========================不被序列化的参数 ============================== */

    private transient String jsonArray;


}
