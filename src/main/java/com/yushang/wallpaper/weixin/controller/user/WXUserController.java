package com.yushang.wallpaper.weixin.controller.user;

import com.yushang.wallpaper.common.config.aop.log.Log;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.layer.model.enums.LogEnum;
import com.yushang.wallpaper.weixin.model.WxUserQueryModel;
import com.yushang.wallpaper.weixin.service.WXUserService;
import com.yushang.wallpaper.weixin.config.WXUserConfig;
import io.swagger.annotations.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 用户模块
 */
@RestController
@RequestMapping(value = "weixin/user")
public class WXUserController {

    @Autowired
    public WXUserService wxUserService;

    @Log(title = "微信跳转获取code", operateType = 1, tabName = "tb_user", logEnum = LogEnum.USER_LOGIN)
    @GetMapping(value = "login", produces = "application/json")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect(WXUserConfig.getCode());
    }

    @Log(title = "获取用户openid和基本信息", operateType = 1, tabName = "tb_user", logEnum = LogEnum.USER_LOGIN)
    @PostMapping(value = "getAccessToken", produces = "application/json")
    public ResultFul login(WxUserQueryModel queryModel) throws Exception {
        return wxUserService.getUserOpenId(queryModel);
    }

    @ApiOperation(value = "获取用户信息", notes = "通过token获取用户信息")
    @RequestMapping(value = "selectUser", method = RequestMethod.GET, produces = "application/json")
    public ResultFul selectUserInfo(WxUserQueryModel queryModel) {
        return wxUserService.selectUserInfo(queryModel);
    }

    @ApiOperation(value = "发送短信", notes = "通过token获取用户信息，阿里云发送短信")
    @RequestMapping(value = "sendPhoneMessage", method = RequestMethod.GET, produces = "application/json")
    public ResultFul sendPhoneMessage(WxUserQueryModel queryModel) throws Exception {
        return wxUserService.sendPhoneMessage(queryModel);
    }

    @ApiOperation(value = "绑定手机号", notes = "通过token绑定手机号或者更换手机号")
    @RequestMapping(value = "reviseUserPhone", method = RequestMethod.POST, produces = "application/json")
    public ResultFul reviseUserPhone(WxUserQueryModel queryModel) {
        return wxUserService.reviseUserPhone(queryModel);
    }

    /**
     * 获取我的订单
     */
    @ApiOperation(value = "获取我的订单", notes = "通过用户id获取我的订单")
    @RequestMapping(value = "getOrderList", method = RequestMethod.POST, produces = "application/json")
    public Map<String, Object> getOrderList(
            @ApiParam(value = "用户id", name = "userId", required = true) @RequestParam Integer userId,
            @ApiParam(value = "状态： 0 全部 1 未付款 2 待发货 3 待收货 4 待评价 5 已完成 6 已退款", name = "status", required = true) @RequestParam Byte status,
            @ApiParam(value = "页码", name = "page", required = true) @RequestParam Integer page,
            @ApiParam(value = "条数", name = "size", required = true) @RequestParam Integer size) {
        return wxUserService.getOrderList(userId, status, page, size);
    }


}