package com.yushang.wallpaper.weixin.controller;

import com.yushang.wallpaper.common.config.aop.wx.WxLog;
import com.yushang.wallpaper.weixin.model.WxUserQueryModel;
import com.yushang.wallpaper.weixin.service.WXPayService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 支付接口
 */
@RestController
@RequestMapping(value = "weixin/wechat")
public class WXPayController {

    @Autowired
    private WXPayService WXPayService;

    @RequestMapping(value = "rechange", method = RequestMethod.POST, produces = "application/json")
    @WxLog(title = "微信充值")
    public Map<String, Object> rechange(WxUserQueryModel queryModel, HttpServletRequest request) throws Exception {
//        return WXPayService.rechange(openid, request);
        return null;
    }

    /**
     * 退款
     */
    @ApiOperation(value = "退款", notes = "统一退款接口")
    @RequestMapping(value = "refundOrder")
    @WxLog(title = "退款")
    public Map<String, Object> refundOrder(
            @ApiParam(value = "订单id", name = "orderId", required = true) @RequestParam("orderId") Integer orderId, // 接受参数(订单Id)
            HttpServletRequest request) throws Exception {
        return WXPayService.refundOrder(orderId, request);
    }


}