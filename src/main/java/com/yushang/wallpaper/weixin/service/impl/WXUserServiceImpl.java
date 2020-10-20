package com.yushang.wallpaper.weixin.service.impl;

import com.yushang.wallpaper.common.config.entity.ResultFul;
import com.yushang.wallpaper.common.config.rabbitmq.SendMessage.SendMessageService;
import com.yushang.wallpaper.common.mapper.OrderMapper;
import com.yushang.wallpaper.common.mapper.user.UserMapper;
import com.yushang.wallpaper.common.pojo.TbOrder;
import com.yushang.wallpaper.common.pojo.user.TbUser;
import com.yushang.wallpaper.weixin.service.WXUserService;
import com.yushang.wallpaper.weixin.utils.WXStringUtils;
import com.yushang.wallpaper.weixin.utils.WXUserConfig;
import com.github.pagehelper.PageHelper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WXUserServiceImpl implements WXUserService {

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private SendMessageService sendMessageService;
	
	@Autowired
	private RedisTemplate<String,Object> redisTemplate;

	@Autowired
	private OrderMapper orderMapper;


	/**
	 * 通过用户id获取信息
	 */
	@Override
	public Map<String,Object> selectUser(int userId) {
		//创建对象
		HashMap<String, Object> param = userMapper.selectUser(userId);
		//向redisTemplate中存入值
		redisTemplate.opsForHash().putAll(param.get("userId")+"",param);
		//返回信息
		return ResultFul.getRowsMap(param);
	}

	/**
	 * 获取用户微信id
	 */
	@Transactional
	@Override
	public Map<String,Object> getUserOpenId(String code) throws Exception {
		//得到拼接后的地址
		String accessTokenUrl = WXUserConfig.getAccessToken(code);
		//把地址转为Utf8编码,打开连接，获取地址
		String result = WXUserConfig.getUrlResult(accessTokenUrl, "utf-8");
		//把返回数据转为json
		JSONObject jsonObject = new JSONObject(result);
		//获取accessToken信息和openid信息
		String accessToken = jsonObject.getString("access_token");
		String openid = jsonObject.getString("openid");
		//查看数据库是否存在该id
		int count = userMapper.selectUserByOpenId(openid);
		Map<String,Object> param = new HashMap<>();
		//不存在
		if (count ==  0){
			//打开获取微信用户个人信息地址
			String userInfoUrl = WXUserConfig.getUserInfoUrl(accessToken, openid);
			String userInfoResult = WXUserConfig.getUrlResult(userInfoUrl, "utf-8");
			JSONObject userJson = new JSONObject(userInfoResult);
			//没有用户信息就创建
			TbUser tbUser = new TbUser();
			tbUser.setOpenId(userJson.getString("openid"));
			tbUser.setUserNick(userJson.getString("nickname"));
			tbUser.setPhoto(userJson.getString("headimgurl"));
//			tbUser.setProvince(userJson.getString("province"));
//			tbUser.setCity(userJson.getString("city"));
			//向数据库查询对象
			userMapper.insertTbUser(tbUser);
			param.put("userId", tbUser.getUserId());
			//向缓存中放入信息
			redisTemplate.opsForHash().put(""+tbUser.getUserId(),"isUse",tbUser.getIsUse());
			return ResultFul.getRowsMap(param);
		}else{
			//查看该用户是否被禁用
			int num = userMapper.selectUserIsUse(openid);
			if(num == 0){
				return  ResultFul.getMessageMap("账号被禁用");
			}else{
				//查询用户信息并返回
				HashMap<String,Object> tbuserParam = userMapper.getUserByOpenId(openid);
				//向缓存中放入信息
				redisTemplate.opsForHash().put(""+tbuserParam.get("userId"),"isUse",tbuserParam.get("isUse"));
				return  ResultFul.getRowsMap(tbuserParam);
			}
		}
	}

	/**
	 * 绑定手机号
	 */
	@Transactional
	@Override
	public Map<String, Object> reviseUserPhone(int userId, String phone, String code) {
		//从redis中获取手机号和验证吗是否存在
		String redisCode = (String) redisTemplate.opsForValue().get(phone);
		if (redisCode == null){
			return ResultFul.getMessageMap("已超时！请重新获取验证码");
		}else if (!redisCode.equalsIgnoreCase(code)){	//如果存在，是否匹配
			return ResultFul.getMessageMap("请输入正确的验证码");
		}
		//验证手机号是否被绑定
		int count = userMapper.selectIsExistPhone(userId,phone);
		if (count != 0){
			return ResultFul.getMessageMap("手机号被绑定！请更换手机号。");
		}
		//保存到数据库
		userMapper.updateMobile(userId,phone);
		return ResultFul.getStatusMap();
	}

	/**
	 * 发送短信
	 */
	@Override
	public Map<String, Object> sendPhoneMessage(String phone) throws Exception {
		//验证手机号是否为空
		boolean empty = StringUtils.isEmpty(phone);
		if(empty){
			return ResultFul.getMessageMap("手机号不能为空");
		}
		//检验手机号是否正常
		boolean phoneIs11 = WXStringUtils.phoneIs11(phone);
		if(!phoneIs11){
			return ResultFul.getMessageMap("手机号应在11位");
		}
		boolean isPhone = WXStringUtils.isPhone(phone);
		if(!isPhone){
			return ResultFul.getMessageMap("手机号不合法");
		}
		//从redis中获取该用户今日发送短信量,超过20条不能发送
		Integer count = (Integer)redisTemplate.opsForValue().get("count" + phone);
		if (count != null && count >= 20){
			return ResultFul.getMessageMap("今日，您已达到发送短信的上限。");
		}
		//从redis中获取上一条手机验证码发送时间，少于60秒不能发送
		String phoneCode = (String) redisTemplate.opsForValue().get(phone);
		if (phoneCode != null){
			return ResultFul.getMessageMap("60秒后，再发送！");
		}
		//发送短信
		sendMessageService.phoneMessage(phone);
		//返回信息
		return ResultFul.getRowsMap("短信已发送");
	}

	/**
	 * 获取我的订单
	 */
	@Override
	public Map<String, Object> getOrderList(Integer userId, Byte status, Integer page, Integer size) {
		PageHelper.startPage(page,size);
		List<TbOrder> orders=orderMapper.getOrderList(userId,status);
		return ResultFul.getListMap(orders,orders.size());
	}

}
