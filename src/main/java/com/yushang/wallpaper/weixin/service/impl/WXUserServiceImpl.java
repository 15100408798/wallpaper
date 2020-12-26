package com.yushang.wallpaper.weixin.service.impl;

import com.github.pagehelper.Page;
import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.config.rabbitmq.SendMessage.SendMessageService;
import com.yushang.wallpaper.common.mapper.order.OrderMapper;
import com.yushang.wallpaper.common.mapper.user.UserMapper;
import com.yushang.wallpaper.common.pojo.order.TbOrder;
import com.yushang.wallpaper.common.pojo.user.TbUser;
import com.yushang.wallpaper.common.utils.CommonUtils;
import com.yushang.wallpaper.common.utils.redis.RedisUtils;
import com.yushang.wallpaper.layer.model.user.user.UserQueryModel;
import com.yushang.wallpaper.layer.model.user.user.UserUpdateModel;
import com.yushang.wallpaper.weixin.model.WxUserQueryModel;
import com.yushang.wallpaper.weixin.service.WXUserService;
import com.yushang.wallpaper.weixin.utils.WXStringUtils;
import com.yushang.wallpaper.weixin.config.WXUserConfig;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang.StringUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class WXUserServiceImpl implements WXUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SendMessageService sendMessageService;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private OrderMapper orderMapper;

    /**
     * 通过token获取用户信息
     *
     * @param queryModel
     * @return
     */
    @Override
    public ResultFul selectUserInfo(WxUserQueryModel queryModel) {
        Objects.requireNonNull(queryModel, "token不能为空");
        String token = queryModel.getToken();
        TbUser tbUser = (TbUser) redisUtils.getValueForOps(token);
        return ResultFul.getSuccessRows(tbUser);
    }

    /**
     * 获取用户微信id
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public ResultFul getUserOpenId(WxUserQueryModel queryModel) throws Exception {
        /** 校验参数 */
        Objects.requireNonNull(queryModel);
        String wxCode = queryModel.getWxCode();
        if (StringUtils.isBlank(wxCode)) {
            return ResultFul.getErrorMessage("微信code不能为空");
        }
        /** 调用微信API，获取微信用户信息 */
        //得到拼接后的地址
        String accessTokenUrl = WXUserConfig.getAccessToken(wxCode);
        //把地址转为Utf8编码,打开连接，获取地址
        String result = WXUserConfig.getUrlResult(accessTokenUrl, "utf-8");
        //把返回数据转为json
        JSONObject jsonObject = new JSONObject(result);
        //获取accessToken信息和openid信息
        String accessToken = jsonObject.getString("access_token");
        String openid = jsonObject.getString("openid");
        /** 查看数据库是否存在该id */
        UserQueryModel userQueryModel = new UserQueryModel();
        userQueryModel.setOpenid(openid);
        Page<TbUser> tbUserPage = userMapper.selectTbUserList(userQueryModel);
        TbUser tbUser = null;
        /** 不存在该用户 */
        if (CollectionUtils.isEmpty(tbUserPage)) {
            //打开获取微信用户个人信息地址
            String userInfoUrl = WXUserConfig.getUserInfoUrl(accessToken, openid);
            String userInfoResult = WXUserConfig.getUrlResult(userInfoUrl, "utf-8");
            JSONObject userJson = new JSONObject(userInfoResult);
            //没有用户信息就创建
            TbUser tbUserInfo = new TbUser();
            tbUserInfo.setOpenId(userJson.getString("openid"));
            tbUserInfo.setUserNick(userJson.getString("nickname"));
            tbUserInfo.setPhoto(userJson.getString("headimgurl"));
//			tbUser.setProvince(userJson.getString("province"));
//			tbUser.setCity(userJson.getString("city"));
            //向数据库查询对象
            userMapper.insertTbUserInfo(null);
        } else {
            //查看该用户是否被禁用
            tbUser = tbUserPage.getResult().get(0);
            if (tbUser.getIsUse() == 1) {
                return ResultFul.getErrorMessage("账号被禁用");
            }
        }
        // 生成token
        String token = CommonUtils.makeUserToken();
        // 向缓存中放入信息
        redisUtils.setValueForOps(token, tbUser, 30 * 60);
        return ResultFul.getSuccessRows(tbUser);
    }

    /**
     * 绑定手机号
     */
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public ResultFul reviseUserPhone(WxUserQueryModel queryModel) {
        Objects.requireNonNull(queryModel, "token不能为空");
        String token = queryModel.getToken();
        TbUser tbUser = (TbUser) redisUtils.getValueForOps(token);
        if (tbUser == null) {
            return ResultFul.getErrorMessage("token已经失效");
        }
        String phone = tbUser.getMobile();
        //从redis中获取手机号和验证吗是否存在
        String redisCode = (String) redisUtils.getValueForOps(phone);
        if (redisCode == null) {
            return ResultFul.getErrorMessage("验证码已超时！请重新获取验证码");
        } else if (!redisCode.equalsIgnoreCase(queryModel.getPhoneCode())) {
            // 如果存在，是否匹配
            return ResultFul.getErrorMessage("请输入正确的验证码");
        }
        /** 验证手机号是否被绑定 */
        UserQueryModel userQueryModel = new UserQueryModel();
        userQueryModel.setMobile(phone);
        Page<TbUser> tbUserPage = userMapper.selectTbUserList(userQueryModel);
        if (!CollectionUtils.isEmpty(tbUserPage)) {
            return ResultFul.getErrorMessage("手机号被绑定！请更换手机号");
        }
        //保存到数据库
        UserUpdateModel userUpdateModel = new UserUpdateModel();
        userUpdateModel.setUserIdValues(new String[]{tbUser.getUserId().toString()});
        userUpdateModel.setMobile(phone);
        int updateCount = userMapper.updateUserInfo(userUpdateModel);
        return ResultFul.getSuccessTotal(updateCount);
    }

    /**
     * 发送短信
     */
    @Override
    public ResultFul sendPhoneMessage(WxUserQueryModel queryModel) throws Exception {
        String token = queryModel.getToken();
        TbUser tbUser = (TbUser) redisUtils.getValueForOps(token);
        if (tbUser == null) {
            return ResultFul.getErrorMessage("token已经失效");
        }
        String phone = tbUser.getMobile();
        //验证手机号是否为空
        if (StringUtils.isEmpty(phone)) {
            return ResultFul.getErrorMessage("手机号不能为空");
        } else if (!WXStringUtils.phoneIs11(phone)) {
            // 检验手机号是否正常
            return ResultFul.getErrorMessage("手机号应在11位");
        } else if (!WXStringUtils.isPhone(phone)) {
            return ResultFul.getErrorMessage("手机号不合法");
        }
        int count = (Integer) redisUtils.getValueForOps("count" + phone);
        if (count > 20) {
            // 从redis中获取该用户今日发送短信量,超过20条不能发送
            return ResultFul.getErrorMessage("今日，您已达到发送短信的上限。");
        }
        //从redis中获取上一条手机验证码发送时间，少于60秒不能发送
        String phoneCode = (String) redisUtils.getValueForOps(phone);
        if (phoneCode != null) {
            return ResultFul.getErrorMessage("60秒后，再发送！");
        }
        //发送短信
        sendMessageService.phoneMessage(phone);
        //返回信息
        return ResultFul.getErrorMessage("短信已发送");
    }

    /**
     * 获取我的订单
     */
    @Override
    public Map<String, Object> getOrderList(Integer userId, Byte status, Integer page, Integer size) {
        PageHelper.startPage(page, size);
        List<TbOrder> orders = orderMapper.getOrderList(userId, status);
        return ResultFul.getListMap(orders, orders.size());
    }

}
