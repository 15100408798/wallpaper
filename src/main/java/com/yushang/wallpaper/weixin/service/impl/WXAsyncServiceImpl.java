package com.yushang.wallpaper.weixin.service.impl;

import com.yushang.wallpaper.common.config.rabbitmq.SendMessage.SendMessageService;
import com.yushang.wallpaper.common.mapper.store.ShopMapper;
import com.yushang.wallpaper.common.mapper.user.UserMapper;
import com.yushang.wallpaper.weixin.service.WXAsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;

@Service
public class WXAsyncServiceImpl implements WXAsyncService {

    @Autowired
    private SendMessageService sendMessageService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ShopMapper shopMapper;

    @Async
    @Override
    public void saveOrderCart(Integer productId, Integer userId, Integer productSkuId, Short number, Byte sendType) {
        HashMap<String,Object> param =  new HashMap<>();
        param.put("productId",productId);
        param.put("userId",userId);
        param.put("productSkuId",productSkuId);
        param.put("number",number);
        param.put("sendType",sendType);
        param.put("createTime",new Date());
        sendMessageService.saveOrderCart(param);
    }

    @Override
    public void sendMessage(Integer shopId, Integer userId, String subscribeDay, String subscribeTime) {
        String goTime = subscribeDay + "  "+subscribeTime;
        //通过userId得到userName、userPhone
        HashMap<String,Object> user = userMapper.selectUserNameById(userId);
        String userPhone = (String) user.get("mobile");
        String username = (String) user.get("userName");
        //得到店铺老板的电话
        String shopUserPhone = shopMapper.getShopPhoneById(shopId);
        //封装发送信息
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("username", username);
        param.put("goTime", goTime);
        param.put("userPhone", userPhone);
        param.put("shopUserPhone", shopUserPhone);
        //发送短信给预约者
        sendMessageService.sendMessage(param);
    }

}
