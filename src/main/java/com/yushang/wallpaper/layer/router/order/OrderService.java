package com.yushang.wallpaper.layer.router.order;

import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.layer.model.order.orderNo.OrderQueryModel;
import com.yushang.wallpaper.layer.model.order.orderNo.OrderUpdateModel;

/**
 * 订单管理
 *
 * @author machao
 * @version 1.0
 * @date 2020/12/9 22:34
 * @Description TODO
 */
public interface OrderService {

    /**
     * 查询订单列表
     *
     * @param orderQueryModel 筛选参数
     * @return 订单列表
     */
    ResultFul selectOrderList(OrderQueryModel orderQueryModel);

    /**
     * 更新订单
     *
     * @param orderUpdateModel 订单信息
     * @return 受影响数量
     */
    ResultFul updateOrderInfo(OrderUpdateModel orderUpdateModel);

}
