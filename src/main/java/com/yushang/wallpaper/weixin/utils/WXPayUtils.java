package com.yushang.wallpaper.weixin.utils;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;

public class WXPayUtils {

	/**
	 *  统一下单
	 */
	public static Map<String, String> createOrder(String outTradeNo, String amountFen, HttpServletRequest request,
												  String openid, String body) throws  Exception {
		// 第二步、编写Map对象
		// 创建hashmap（用户获得签名）
		SortedMap<String, String> paraMap = new TreeMap<String, String>();
		// 设置请求参数(小程序ID)
		paraMap.put("appid", WXConfig.WX_APPLYID);
		// 设置请求参数(商户号)
		paraMap.put("mch_id", WXConfig.WX_MCHID);
		// 设置随机字符串
		paraMap.put("nonce_str", WXStringUtils.getRandomStringByLength(32));
		// 设置body变量 (支付成功显示在微信支付 商品详情中)
		paraMap.put("body", body);
		// 设置商户订单号
		paraMap.put("out_trade_no", outTradeNo);
		// 设置请求参数(总金额:分)
		paraMap.put("total_fee", amountFen);
		// 设置请求参数(终端IP)
		paraMap.put("spbill_create_ip", WebUtils.getIpAddress(request));
		// 设置请求参数(通知地址)
		paraMap.put("notify_url",WebUtils.getBasePath()+ "/weixin/wechat/payCallback");
		// 设置请求参数(交易类型)
		paraMap.put("trade_type", "JSAPI");
		// 设置请求参数(openid)(在接口文档中 该参数 是否必填项 但是一定要注意 如果交易类型设置成'JSAPI'则必须传入openid)
		paraMap.put("openid", openid);
		// 调用逻辑传入参数按照字段名的 ASCII 码从小到大排序（字典序）
		String stringA = WebUtils.formatUrlMap(paraMap, false, false);
		// 在stringA最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue。(签名)
		String sign = MD5Util.MD5(stringA + "&key=" + WXConfig.WX_SHOPKEY).toUpperCase();
		// 第三步、将参数 编写XML格式
		StringBuffer paramBuffer = new StringBuffer();
		paramBuffer.append("<xml>");
		paramBuffer.append("<appid>" + WXConfig.WX_APPLYID + "</appid>");
		paramBuffer.append("<mch_id>" + WXConfig.WX_MCHID + "</mch_id>");
		paramBuffer.append("<nonce_str>" + paraMap.get("nonce_str") + "</nonce_str>");
		paramBuffer.append("<sign>" + sign + "</sign>");
		paramBuffer.append("<body>" + body + "</body>");
		paramBuffer.append("<out_trade_no>" + paraMap.get("out_trade_no") + "</out_trade_no>");
		paramBuffer.append("<total_fee>" + paraMap.get("total_fee") + "</total_fee>");
		paramBuffer.append("<spbill_create_ip>" + paraMap.get("spbill_create_ip") + "</spbill_create_ip>");
		paramBuffer.append("<notify_url>" + paraMap.get("notify_url") + "</notify_url>");
		paramBuffer.append("<trade_type>" + paraMap.get("trade_type") + "</trade_type>");
		paramBuffer.append("<openid>" + paraMap.get("openid") + "</openid>");
		paramBuffer.append("</xml>");
		System.out.println(paramBuffer.toString());
		// 第四步、发送请求(POST)(获得数据包ID)(这有个注意的地方
		// 如果不转码成ISO8859-1，则会告诉你body不是UTF8编码 就算你改成UTF8编码也一样不好使
		// 所以修改成ISO8859-1)
		Map<String, String> map = WebUtils.doXMLParse(WebUtils.getRemotePortData(WXConfig.createUnifiedOrder,
				new String(paramBuffer.toString().getBytes(), "ISO8859-1")));
		return map;
	}

	/**
	 * 再次签名
	 * @return
	 */
	public static JSONObject generateSignature(String prepayId) throws Exception {
		// 实例化返回对象
		JSONObject resultJson = new JSONObject();
		// 创建 时间戳
		String timeStamp = Long.valueOf(System.currentTimeMillis()).toString();
		// 创建 随机串
		String nonceStr = WebUtils.getRandomStringByLength(32);
		// 创建 MD5
		String signType = "MD5";
		// 创建hashmap(用户获得签名)
		SortedMap<String, String> paraMap = new TreeMap<String, String>();
		// 设置(小程序ID)(这块一定要是大写)
		paraMap.put("appId", WXConfig.WX_APPLYID);
		// 设置(时间戳)
		paraMap.put("timeStamp", timeStamp);
		// 设置(随机串)
		paraMap.put("nonceStr", nonceStr);
		// 设置(数据包)
		paraMap.put("package", "prepay_id=" + prepayId);
		// 设置(签名方式)
		paraMap.put("signType", signType);

		// 调用逻辑传入参数按照字段名的 ASCII 码从小到大排序（字典序）
		String stringA = WebUtils.formatUrlMap(paraMap, false, false);
		// 第二步，在stringA最后拼接上key得到stringSignTemp字符串，并对stringSignTemp进行MD5运算，再将得到的字符串所有字符转换为大写，得到sign值signValue。(签名)
		String sign = MD5Util.MD5(stringA + "&key=" + WXConfig.WX_SHOPKEY).toUpperCase();

		if (StringUtils.isNotBlank(sign)) {
			// 返回签名信息
			resultJson.put("paySign", sign);
			// 返回随机串(这个随机串是新创建的)
			resultJson.put("nonceStr", nonceStr);
			// 返回时间戳
			resultJson.put("timeStamp", timeStamp);
			// 返回数据包
			resultJson.put("package", "prepay_id=" + prepayId);
			// 返回签名算法
			resultJson.put("signType", "MD5");
		}
		return resultJson;
	}
}
