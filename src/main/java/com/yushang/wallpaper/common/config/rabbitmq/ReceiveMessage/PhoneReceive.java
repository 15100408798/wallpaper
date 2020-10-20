package com.yushang.wallpaper.common.config.rabbitmq.ReceiveMessage;

import com.yushang.wallpaper.common.utils.log.LoggerUtils;
import com.yushang.wallpaper.weixin.utils.WXSendMessage;
import com.yushang.wallpaper.weixin.utils.WXStringUtils;
import com.yushang.wallpaper.common.config.rabbitmq.config.ConstantsConfig;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Component
public class PhoneReceive {

	@Autowired
	private RedisTemplate<String,Object> redisTemplate;

	@RabbitListener(queues= ConstantsConfig.PHONEQUEUE)
	@RabbitHandler
	public void sendPhoneMessage1(byte[] bytes,Message message,Channel channel){
		sendPhoneMessage(bytes,message,channel);
	}

	@RabbitListener(queues=ConstantsConfig.PHONEQUEUE)
	@RabbitHandler
	public void sendPhoneMessage2(byte[] bytes,Message message,Channel channel){
		sendPhoneMessage(bytes,message,channel);
	}

	private void sendPhoneMessage(byte[] bytes,Message message,Channel channel){
		try{
			//获得验证码
			String code = WXSendMessage.getCode();
			String phone = new String(bytes,"UTF-8");
			//发送短信
			WXSendMessage.sendPhoneMessage(phone, code);
			//向redis缓存中存储这个手机号+验证码
			redisTemplate.opsForValue().set(phone,code,60,TimeUnit.SECONDS);
			//向redis缓存中存储今天这个手机是第几次发送短信
			Object count = redisTemplate.opsForValue().get("count" + phone);
			if (count == null){
				redisTemplate.opsForValue().set("count" + phone,1, WXStringUtils.secondTime(),TimeUnit.SECONDS);
			}else{
				redisTemplate.opsForValue().increment("count" + phone,1);
			}
			//告知队列可以删除这条信息
			channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
		}catch (Exception e){
			LoggerUtils.error("发送短信异常",e);
		}
	}

	@RabbitListener(queues=ConstantsConfig.SHOPQUEUE)
	@RabbitHandler
	public void sendPhoneMessage1(HashMap<String,Object> param,Message message, Channel channel){
		try{
			String username = (String) param.get("username");
			String goTime = (String) param.get("goTime");
			String userPhone = (String) param.get("userPhone");
			String shopUserPhone = (String) param.get("shopUserPhone");
			//发送短信
			WXSendMessage.sendMessageToUser(username, goTime, userPhone, shopUserPhone);
			//告知队列可以删除这条信息
			channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
		}catch (Exception e){
			LoggerUtils.error("给店家发送短信异常",e);
		}
	}


}
