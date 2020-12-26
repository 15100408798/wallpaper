package com.yushang.wallpaper.layer.model.order.orderNo;

import com.yushang.wallpaper.layer.model.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

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
public class OrderUpdateModel extends BaseModel {

    private static final long serialVersionUID = 8424397139710762655L;
    /**
     * 订单ID
     */
    private String orderIds;
    /**
     * 删除状态：0-未删除，1-已删除
     */
    private Integer deleteFlag;
    /**
     * 订单ID数组
     */
    private String[] orderIdValues;
    /**
     * 发货时间
     */
    private Date sendTime;
    /**
     * 订单状态：
     * 1-未付款，2-待发货（已付款），3-待收货
     * 4-待评价，5-已完成，6-已退款
     */
    private Integer orderStatus;
    /**
     * 完成时间
     */
    private Date completeTime;

    public void validOrderIdIsNotNull() {
        Objects.requireNonNull(orderIds);
    }

}
