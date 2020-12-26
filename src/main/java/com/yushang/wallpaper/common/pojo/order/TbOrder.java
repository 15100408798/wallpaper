package com.yushang.wallpaper.common.pojo.order;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 */
@Setter
@Getter
@NoArgsConstructor
public class TbOrder implements Serializable {

    private static final long serialVersionUID = 6169439498637132783L;
    /**
     * 订单表ID
     */
    private Integer orderId;
    /**
     * 订单编号   RyyyyMMdd+随机4位+0000001
     */
    private String orderNo;
    /**
     * 订单状态：
     * 1-未付款，2-待发货（已付款），3-待收货
     * 4-待评价，5-已完成，6-已退款
     */
    private Byte status;
    /**
     * 总金额
     */
    private BigDecimal allMoney;
    /**
     * 优惠金额
     */
    private BigDecimal minusMoney;
    /**
     * 实际付款金额
     */
    private BigDecimal payMoney;
    /**
     * 使用积分(100:1)
     */
    private Short score;
    /**
     * 支付方式
     * 1-微信, 2-余额（全款）3-支付宝
     */
    private Byte payType;
    /**
     * 收获地址Id
     */
    private Integer addressId;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GTM+8")
    private Date createTime;
    /**
     * 付款时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GTM+8")
    private Date payTime;
    /**
     * 发货时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GTM+8")
    private Date sendTime;
    /**
     * 完成时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GTM+8")
    private Date completeTime;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Byte deleteFlag;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 商户id
     */
    private Integer shopId;
    /**
     * 配送方式: 1-自提，2-快递
     */
    private Byte sendType;
    /**
     * 物流公司
     */
    private String logisticsName;
    /**
     * 物流订单号
     */
    private String logisticsNumber;

}
