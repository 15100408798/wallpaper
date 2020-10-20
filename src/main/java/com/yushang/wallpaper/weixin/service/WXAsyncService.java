package com.yushang.wallpaper.weixin.service;

public interface WXAsyncService {

    /**
     * 下订单
     */
    void saveOrderCart(Integer productId, Integer userId, Integer productSkuId, Short number, Byte sendType);

    void sendMessage(Integer shopId, Integer userId, String subscribeDay, String subscribeTime);


}
