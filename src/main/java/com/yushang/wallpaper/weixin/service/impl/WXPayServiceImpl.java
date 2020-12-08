package com.yushang.wallpaper.weixin.service.impl;

import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.mapper.OrderMapper;
import com.yushang.wallpaper.weixin.service.WXPayService;
import com.yushang.wallpaper.weixin.utils.WXPayUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class WXPayServiceImpl implements WXPayService {

	@Autowired
	private OrderMapper orderMapper;

	/**
	 * 充值
	 */
	@Transactional
	@Override
	public Map<String,Object> rechange(String openid,  HttpServletRequest request) throws Exception {
//		Map<String, String> map = WXPayUtils.createOrder(outTradeNo, amountFen, request, openid,"余额充值");
//		if (map != null && map.get("return_code").equals("SUCCESS")) {
//			// "微信 统一下单 接口调用成功 修改支付信息成功"
//			// 获得签名
//			JSONObject jsonObject = WXPayUtils.generateSignature(map.get("prepay_id"));
//			if (jsonObject != null && !jsonObject.equals("")) {
//				return ResultFul.getRowsMap(jsonObject.toString());
//			} else {
//				return ResultFul.getMessageMap("获得签名失败");
//			}
//		} else {
//			return ResultFul.getMessageMap("微信 统一下单 接口调用失败");
//		}
		return null;
	}

	/**
	 * 退款
	 */
	@Override
	public  Map<String,Object> refundOrder(Integer orderId, HttpServletRequest request)throws Exception {
		return null;
	}



}
