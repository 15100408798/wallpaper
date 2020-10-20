package com.yushang.wallpaper.weixin.service;

import java.util.Map;

public interface WXUserService {

    Map<String,Object> selectUser(int userId);

    /**
     * 获取用户微信id
     */
    Map<String,Object> getUserOpenId(String code) throws  Exception;

    /**
	 * 绑定手机号
	 */
	Map<String, Object> reviseUserPhone(int userId, String phone, String code);

	/**
	 * 发送短信
	 */
	Map<String, Object> sendPhoneMessage(String phone) throws Exception;

	/**
	 * 获取我的订单
	 */
    Map<String, Object> getOrderList(Integer userId, Byte status, Integer page, Integer size);
}
