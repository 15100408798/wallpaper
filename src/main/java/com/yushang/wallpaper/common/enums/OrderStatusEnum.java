package com.yushang.wallpaper.common.enums;

import lombok.Getter;

/**
 * 订单状态
 * @author machao
 * @version 1.0
 * @date 2020/12/12 16:09
 * @Description TODO
 */
@Getter
public enum OrderStatusEnum {
    /**
     * 订单状态：
     * 1-未付款，2-待发货（已付款），3-待收货
     * 4-待评价，5-已完成，6-已退款
     */
    ORDER_UNPAID(1, "订单未付款"),
    ORDER_DELIVERED(2, "订单待发货"),
    ORDER_RECEIVED(3, "订单待收货"),
    ORDER_EVALUATED(4, "订单待评价"),
    ORDER_DONE(5, "订单已完成"),
    ORDER_REFUND(6, "订单已退款"),


    ;

    private final int code;
    private final String message;

    OrderStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
