package com.yushang.wallpaper.weixin.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface WXPayService {

	/**
	 * 充值
	 */
	Map<String,Object> rechange(String openid,HttpServletRequest request) throws Exception;

	/**
	 * 退款
	 */
    Map<String, Object> refundOrder(Integer orderId, HttpServletRequest request) throws Exception;
}
