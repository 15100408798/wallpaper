package com.yushang.wallpaper.weixin.service;

import java.util.Map;

public interface WXShopService {

    Map<String, Object> shopList(Integer type, Integer page, Integer size);

    Map<String, Object> addShoppingCart(Integer productId, Integer userId, Integer productSkuId, Short number, Byte sendType);

    Map<String, Object> getWXBaoYang(Integer page, Integer size, String longitude, String latitude);

    Map<String, Object> addSubsribe(Integer shopId, Integer userId, String subscribeDay, String subscribeTime);

    Map<String, Object> shopLabel(Byte typeId);

}
