package com.yushang.wallpaper.layer.service.impl;

import com.yushang.wallpaper.common.mapper.CustomeMapper;
import com.yushang.wallpaper.common.mapper.OrderMapper;
import com.yushang.wallpaper.layer.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

@Service
@Async
public class AsyncServiceImpl implements AsyncService {

    @Autowired
    private CustomeMapper customeMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void updateCustomeService(Byte customServiceId) {
        customeMapper.updateCustomeService(customServiceId);
    }

    @Override
    public void saveShopOneWeek(Long id, Date sevenDayTime, Date nowTime) {
        HashMap<String,Object> param = new HashMap<>();
        //获得这个店铺在七天内的交易金额
        BigDecimal sevenMoney = orderMapper.selectSevenDayMoney();
        //获得实际交易金额
        BigDecimal payMoney = orderMapper.selectPayMoney();
        param.put("createTime",nowTime);
        param.put("shopId",id);
        param.put("startTime",sevenDayTime);
        param.put("endTime",nowTime);
        param.put("allMoney",sevenMoney);
        param.put("payMoney",payMoney);
        //保存到数据库
        orderMapper.saveShopOneWeek(param);
    }
}
