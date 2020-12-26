package com.yushang.wallpaper.layer.model.order.orderNo;

import com.yushang.wallpaper.layer.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 订单查询
 * @author machao
 * @version 1.0
 * @date 2020/12/11 20:57
 * @Description TODO
 */
@Setter
@Getter
@NoArgsConstructor
public class OrderQueryModel extends BaseModel {

    private static final long serialVersionUID = 4065870012389736901L;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 商户ID
     */
    private Integer shopId;
    /**
     * 订单状态：
     * 1-未付款，2-待发货（已付款），3-待收货
     * 4-待评价，5-已完成，6-已退款
     */
    private Integer orderStatus;
    /**
     * 支付方式
     * 1-微信, 2-余额（全款）3-支付宝
     */
    private Integer payType;
    /**
     * 配送方式: 1-自提，2-快递
     */
    private Integer sendType;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Integer deleteFlag;


}
