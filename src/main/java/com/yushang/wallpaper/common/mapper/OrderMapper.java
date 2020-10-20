package com.yushang.wallpaper.common.mapper;

import com.yushang.wallpaper.common.pojo.TbOrder;
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

}
