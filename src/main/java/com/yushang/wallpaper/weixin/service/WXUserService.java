package com.yushang.wallpaper.weixin.service;

import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.weixin.model.WxUserQueryModel;
import org.springframework.lang.NonNull;

import java.util.Map;

public interface WXUserService {

    /**
     * 通过token获取用户信息
     *
     * @param queryModel token
     * @return 用户信息
     */
    ResultFul selectUserInfo(WxUserQueryModel queryModel);

    /**
     * 通过微信code获取用户信息
     * 1、当用户信息不存在时，自动添加该用户信息
     *
     * @param queryModel 微信code
     * @return 用户信息
     */
    ResultFul getUserOpenId(@NonNull WxUserQueryModel queryModel) throws Exception;

    /**
     * 绑定手机号
     */
    ResultFul reviseUserPhone(WxUserQueryModel queryModel);

    /**
     * 发送短信
     */
    ResultFul sendPhoneMessage(WxUserQueryModel queryModel) throws Exception;

    /**
     * 获取我的订单
     */
    Map<String, Object> getOrderList(Integer userId, Byte status, Integer page, Integer size);
}
