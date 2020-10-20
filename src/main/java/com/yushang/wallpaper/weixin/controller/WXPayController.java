package com.yushang.wallpaper.weixin.controller;

import com.yushang.wallpaper.common.config.aop.WxAop;
import com.yushang.wallpaper.weixin.service.WXPayService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@RequestMapping(value = "weixin/wechat")
@Api(value = "order 接口",tags = "order",description = "订单、支付接口")
public class WXPayController {

    @Autowired
    private WXPayService WXPayService;

    /**
     * 微信充值
     */
    @ApiOperation(value="充值",notes="微信充值")
    @ApiResponses({
            @ApiResponse(code = 200,message = "支付成功"),
            @ApiResponse(code = 400,message = "支付失败")
    })
    @RequestMapping(value = "rechange",method = RequestMethod.POST,produces = "application/json")
    @WxAop
    public Map<String,Object> rechange(
            @ApiParam(name = "openid",value = "微信openid",required = true)  @RequestParam String openid, // 微信openid
            HttpServletRequest request) throws Exception {
          return  WXPayService.rechange(openid, request);
    }

    /**
     * 退款
     */
    @ApiOperation(value="退款",notes="统一退款接口")
    @ApiResponses({
            @ApiResponse(code = 200,message = "退款成功"),
            @ApiResponse(code = 400,message = "退款失败")
    })
    @RequestMapping(value = "refundOrder")
    @WxAop
    public Map<String,Object> refundOrder(
            @ApiParam(value = "订单id",name = "orderId",required = true) @RequestParam("orderId") Integer orderId, // 接受参数(订单Id)
            HttpServletRequest request) throws Exception {
           return WXPayService.refundOrder(orderId,request);
    }


}