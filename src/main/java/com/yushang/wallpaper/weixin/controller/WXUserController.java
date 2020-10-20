package com.yushang.wallpaper.weixin.controller;

import com.yushang.wallpaper.common.config.aop.WxAop;
import com.yushang.wallpaper.weixin.service.WXUserService;
import com.yushang.wallpaper.weixin.utils.WXUserConfig;
import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


/**
 * 个人中心
 */
@RestController
@RequestMapping(value = "weixin/user")
@Api(value = "User Api接口",tags = "user",description = "User Api接口文档")
public class WXUserController {

	@Autowired
	public WXUserService wxUserService;


	/**
	 *	第一、通过code，获取用户openid和基本信息。
	 *	然后取数据库查询是否存在该用户，如果没有，则添加。存在，即登陆
	 */
	@ApiOperation(value = "获得code",notes = "通过code，获取用户openid和基本信息")
	@ApiResponses({
			@ApiResponse(code = 200,message = "获取code成功"),
			@ApiResponse(code = 400,message = "获取code失败")
	})
	@GetMapping(value = "login",produces = "application/json")
	@WxAop
	public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.sendRedirect(WXUserConfig.getCode());
	}

	
	@ApiOperation(value = "获取用户openid和基本信息",notes = "通过code，获取用户openid和基本信息(自动跳转)")
	@ApiResponses({
			@ApiResponse(code = 200,message = "获取用户信息成功"),
			@ApiResponse(code = 400,message = "获取用户信息失败")
	})
	@PostMapping(value = "getAccessToken",produces = "application/json")
	@WxAop
	public Map<String,Object> login(
			@ApiParam(name = "code",value = "微信code",required = true) @RequestParam String code) throws Exception {
		return wxUserService.getUserOpenId(code);
	}


	/**
	 * 通过用户id获取用户信息
	 */
	@ApiOperation(value = "获取用户信息",notes = "通过用户id获取用户信息")
	@ApiResponses({
			@ApiResponse(code = 200,message = "成功"),
			@ApiResponse(code = 400,message = "访问出错")
	})
	@RequestMapping(value = "selectUser",method = RequestMethod.POST,produces="application/json")
	@WxAop
	public Map<String,Object> selectUser(
			@ApiParam(name = "userId",value = "用户id",required = true)  @RequestParam int userId){
		return wxUserService.selectUser(userId);
	}


	/**
	 * 发送短信
	 */
	@ApiOperation(value = "发送短信",notes = "阿里云发送短信")
	@ApiResponses({
			@ApiResponse(code = 200,message = "成功"),
			@ApiResponse(code = 400,message = "发送出错")
	})
	@RequestMapping(value = "sendPhoneMessage",method = RequestMethod.GET,produces = "application/json")
	@WxAop
	public Map<String,Object> sendPhoneMessage(
			@RequestParam(value = "phone") String phone) throws Exception{
		return wxUserService.sendPhoneMessage(phone);
		
	}
	
	
	/**
	 * 绑定手机号
	 */
	@ApiOperation(value="绑定手机号",notes="通过用户id绑定手机号或者更换手机号")
	@ApiResponses({
		@ApiResponse(code = 200,message = "成功"),
		@ApiResponse(code = 400,message = "访问出错")
	})
	@RequestMapping(value = "reviseUserPhone",method = RequestMethod.POST,produces = "application/json")
	@WxAop
	public Map<String,Object> reviseUserPhone(
			@ApiParam(value="用户id",name="userId",required=true) @RequestParam int userId,
			@ApiParam(value="手机号码",name="phone",required=true) @RequestParam String phone, 
			@ApiParam(value="验证码",name="code",required=true) @RequestParam String code) {
		return wxUserService.reviseUserPhone(userId,phone,code);
	}

	/**
	 * 获取我的订单
	 */
	@ApiOperation(value="获取我的订单",notes="通过用户id获取我的订单")
	@ApiResponses({
			@ApiResponse(code = 200,message = "成功"),
			@ApiResponse(code = 400,message = "访问出错")
	})
	@RequestMapping(value = "getOrderList",method = RequestMethod.POST,produces = "application/json")
	@WxAop
	public  Map<String,Object>  getOrderList(
			@ApiParam(value="用户id",name="userId",required=true) @RequestParam Integer userId,
			@ApiParam(value="状态： 0 全部 1 未付款 2 待发货 3 待收货 4 待评价 5 已完成 6 已退款",name="status",required=true) @RequestParam Byte status,
			@ApiParam(value="页码",name="page",required=true) @RequestParam Integer page,
			@ApiParam(value="条数",name="size",required=true) @RequestParam Integer size){
			return wxUserService.getOrderList(userId,status,page,size);
	}



}