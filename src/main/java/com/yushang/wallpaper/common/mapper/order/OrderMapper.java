package com.yushang.wallpaper.common.mapper.order;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.pojo.order.TbOrder;
import com.yushang.wallpaper.layer.model.order.orderNo.OrderQueryModel;
import com.yushang.wallpaper.layer.model.order.orderNo.OrderUpdateModel;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public interface OrderMapper {

    List<TbOrder> getOrderList(@Param(value = "userId") Integer userId, @Param(value = "status")  Byte status);

    TbOrder selectOrderByNo(@Param(value = "outTradeNo") String outTradeNo);

    TbOrder selectOrderById(@Param(value = "orderId") Integer orderId);

    List<HashMap<String, Object>> selectStoreMoneyList();

    int selectCount();

    BigDecimal selectSevenDayMoney();

    BigDecimal selectPayMoney();

    void saveShopOneWeek(HashMap<String, Object> param);

    /**
     * 查询订单列表
     *
     * @param orderQueryModel 筛选参数
     * @return 订单列表
     */
    Page<TbOrder> selectOrderList(OrderQueryModel orderQueryModel);

    /**
     * 更新订单
     *
     * @param orderUpdateModel 订单信息
     * @return 受影响数量
     */
    int updateOrderInfo(OrderUpdateModel orderUpdateModel);
}
