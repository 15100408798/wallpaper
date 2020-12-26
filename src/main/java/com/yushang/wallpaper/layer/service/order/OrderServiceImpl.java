package com.yushang.wallpaper.layer.service.order;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.mapper.order.OrderMapper;
import com.yushang.wallpaper.common.pojo.order.TbOrder;
import com.yushang.wallpaper.layer.model.order.orderNo.OrderQueryModel;
import com.yushang.wallpaper.layer.model.order.orderNo.OrderUpdateModel;
import com.yushang.wallpaper.layer.router.order.OrderService;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * 订单管理
 * @author machao
 * @version 1.0
 * @date 2020/12/9 22:34
 * @Description TODO
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderMapper orderMapper;

    /**
     * 查询订单列表
     *
     * @param orderQueryModel 筛选参数
     * @return 订单列表
     */
    @Transactional(readOnly = true)
    @Override
    public ResultFul selectOrderList(OrderQueryModel orderQueryModel) {
        /** 校验参数, 分页 */
        orderQueryModel.validPageSizeIsNull();
        orderQueryModel.startPage();
        /** 查询订单列表 */
        Page<TbOrder> tbOrderList = orderMapper.selectOrderList(orderQueryModel);
        return ResultFul.getSuccessList(tbOrderList.getResult(), tbOrderList.getTotal());
    }

    /**
     * 更新订单
     *
     * @param orderUpdateModel 订单信息
     * @return 受影响数量
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public ResultFul updateOrderInfo(OrderUpdateModel orderUpdateModel) {
        /* 校验参数 */
        Objects.requireNonNull(orderUpdateModel);
        orderUpdateModel.validOrderIdIsNotNull();
        String orderIds = orderUpdateModel.getOrderIds();
        String[] orderIdValues = orderIds.split(",");
        if (ArrayUtils.isEmpty(orderIdValues)){
            throw new NullPointerException();
        }
        orderUpdateModel.setOrderIdValues(orderIdValues);
        // 更新订单信息
        int updateCount = orderMapper.updateOrderInfo(orderUpdateModel);
        if (updateCount != orderIdValues.length) {
            throw new RuntimeException("更新订单信息失败");
        }
        return ResultFul.getSuccessTotal(updateCount);
    }


}
