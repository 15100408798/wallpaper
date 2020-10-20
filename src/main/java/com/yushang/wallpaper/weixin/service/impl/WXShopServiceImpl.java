package com.yushang.wallpaper.weixin.service.impl;

import com.github.pagehelper.PageHelper;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.mapper.*;
import com.yushang.wallpaper.common.mapper.store.ProductLabelMapper;
import com.yushang.wallpaper.common.mapper.store.ProductMapper;
import com.yushang.wallpaper.common.mapper.store.ShopMapper;
import com.yushang.wallpaper.common.mapper.user.UserMapper;
import com.yushang.wallpaper.weixin.service.WXAsyncService;
import com.yushang.wallpaper.weixin.service.WXShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WXShopServiceImpl implements WXShopService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductLabelMapper productLabelMapper;

    @Autowired
    private WXAsyncService wxAsyncService;

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private SubscribeMapper subscribeMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public Map<String, Object> shopList(Integer type, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        List<HashMap<String, Object>> list = productMapper.shopList(type);
        return ResultFul.getRowsMap(list);
    }

    @Override
    public Map<String, Object> shopLabel(Byte typeId) {
        List<HashMap<String, Object>> list = productLabelMapper.shopLabel(typeId);
        return ResultFul.getRowsMap(list);
    }


    @Transactional
    @Override
    public Map<String, Object> addShoppingCart(Integer productId, Integer userId, Integer productSkuId, Short number, Byte sendType) {
        //异步发送
        wxAsyncService.saveOrderCart(productId,userId,productSkuId,number,sendType);
        //返回消息
        return ResultFul.getStatusMap();
    }

    /**
     * 商城 维修保养
     */
    @Override
    public Map<String, Object> getWXBaoYang(Integer page, Integer size, String longitude, String latitude) {
        PageHelper.startPage(page, size);
        List<Map<String, Object>> list =  shopMapper.getWXBaoYang();
        return ResultFul.getRowsMap(list);
    }

    /**
     * 发送短信通知预约人
     */
    @Transactional
    @Override
    public Map<String, Object> addSubsribe(Integer shopId, Integer userId, String subscribeDay, String subscribeTime) {
        //保存到数据库
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("shopId", shopId);
        param.put("userId", userId);
        param.put("subscribeDay", subscribeDay);
        param.put("subscribeTime", subscribeTime);
        param.put("createTime", new Date());
        subscribeMapper.addSubsribe(param);
        //异步发送消息
        wxAsyncService.sendMessage(shopId, userId,subscribeDay, subscribeTime);
        return ResultFul.getStatusMap();
    }




}
