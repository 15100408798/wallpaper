package com.yushang.wallpaper.layer.controller.order;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.aop.shiro.PermissionName;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.enums.OrderStatusEnum;
import com.yushang.wallpaper.common.enums.StatusEnum;
import com.yushang.wallpaper.layer.model.enums.LogEnum;
import com.yushang.wallpaper.layer.model.order.orderNo.OrderQueryModel;
import com.yushang.wallpaper.layer.model.order.orderNo.OrderUpdateModel;
import com.yushang.wallpaper.layer.router.order.OrderService;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/layer/order/")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PermissionName(value = "查询订单列表")
    @RequiresPermissions(value = {"system:selectOrderList"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "查询订单列表", tabName = "tb_order", operateType = 1, logEnum = LogEnum.ORDER_LIST)
    @RequestMapping("selectOrderList")
    public ResultFul selectOrderList(OrderQueryModel orderQueryModel) {
        return orderService.selectOrderList(orderQueryModel);
    }

    @PermissionName(value = "删除订单")
    @RequiresPermissions(value = {"system:deleteOrder"})
    @RequiresRoles(value = {"1"}, logical = Logical.OR)
    @Log(title = "删除订单", tabName = "tb_order", operateType = 2, logEnum = LogEnum.DEL_ORDER)
    @RequestMapping("deleteOrder")
    public ResultFul deleteOrder(OrderUpdateModel orderUpdateModel) {
        orderUpdateModel.setDeleteFlag(StatusEnum.DELETE_YES.getCode());
        return orderService.updateOrderInfo(orderUpdateModel);
    }

    @PermissionName(value = "订单发货")
    @RequiresPermissions(value = {"system:sendOrder"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "订单发货", tabName = "tb_order", operateType = 9, logEnum = LogEnum.SEND_ORDER)
    @RequestMapping("sendOrder")
    public ResultFul sendOrder(OrderUpdateModel orderUpdateModel) {
        orderUpdateModel.setSendTime(new Date());
        orderUpdateModel.setOrderStatus(OrderStatusEnum.ORDER_RECEIVED.getCode());
        return orderService.updateOrderInfo(orderUpdateModel);
    }

    @PermissionName(value = "订单收货")
    @RequiresPermissions(value = {"system:harvestOrder"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "订单收货", tabName = "tb_order", operateType = 13, logEnum = LogEnum.HARVEST_ORDER)
    @RequestMapping("harvestOrder")
    public ResultFul harvestOrder(OrderUpdateModel orderUpdateModel) {
        orderUpdateModel.setOrderStatus(OrderStatusEnum.ORDER_EVALUATED.getCode());
        return orderService.updateOrderInfo(orderUpdateModel);
    }

    @PermissionName(value = "订单完结")
    @RequiresPermissions(value = {"system:completeOrder"})
    @RequiresRoles(value = {"1", "2"}, logical = Logical.OR)
    @Log(title = "订单完结", tabName = "tb_order", operateType = 13, logEnum = LogEnum.COMPLETE_ORDER)
    @RequestMapping("completeOrder")
    public ResultFul completeOrder(OrderUpdateModel orderUpdateModel) {
        orderUpdateModel.setCompleteTime(new Date()); // 完结时间
        orderUpdateModel.setOrderStatus(OrderStatusEnum.ORDER_DONE.getCode());
        return orderService.updateOrderInfo(orderUpdateModel);
    }

    @PermissionName(value = "订单退货")
    @RequiresPermissions(value = {"system:refundOrder"})
    @RequiresRoles(value = {"1"}, logical = Logical.OR)
    @Log(title = "订单退货", tabName = "tb_order", operateType = 13, logEnum = LogEnum.REFUND_ORDER)
    @RequestMapping("refundOrder")
    public ResultFul refundOrder(OrderUpdateModel orderUpdateModel) {
        orderUpdateModel.setOrderStatus(OrderStatusEnum.ORDER_REFUND.getCode());
        return orderService.updateOrderInfo(orderUpdateModel);
    }

}

