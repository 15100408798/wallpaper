package com.yushang.wallpaper.common.config.rabbitmq.SendMessage.impl;

import com.yushang.wallpaper.common.config.rabbitmq.SendMessage.SendMessageService;
import com.yushang.wallpaper.common.config.rabbitmq.config.ConstantsConfig;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.UUID;

@Component
public class SendMessageServiceImpl implements SendMessageService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 消息回调确认方法
     *
     * @param correlationData 回调数据
     * @param ack             是否发送成功
     * @param
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        System.out.println("confirm回调方法>>>>>>>>>>>>>回调消息ID为: " + correlationData.getId());
        if (ack) {
            System.out.println("confirm回调方法>>>>>>>>>>>>>消息发送成功");
        } else {
            System.out.println("confirm回调方法>>>>>>>>>>>>>消息发送失败" + cause);
        }

    }

    /**
     * 保存日志
     *
     * @param message 发送内容
     */
    @Override
    public void saveLog(Object message) {
        //设置回调对象
        rabbitTemplate.setConfirmCallback(this);
        //构建回调返回的数据
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //发送消息
        rabbitTemplate.convertAndSend(ConstantsConfig.LOGExchangeName, ConstantsConfig.LOGRoutingKey, message, correlationData);

    }

    /**
     * 发送手机号
     *
     * @param phone
     * @throws UnsupportedEncodingException
     */
    @Override
    public void phoneMessage(String phone) throws UnsupportedEncodingException {
        //设置消息转换器
        rabbitTemplate.setMessageConverter(new SimpleMessageConverter());
        //设置回调
        rabbitTemplate.setConfirmCallback(this);
        //构建回调返回的数据
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //发送消息
        rabbitTemplate.convertAndSend(ConstantsConfig.PHONEEXCHANGENAME, ConstantsConfig.PHONEROUTINGKEY, phone.getBytes("UTF-8"), correlationData);
    }

    /**
     * 发送通知给商户老板
     */
    @Override
    public void sendMessage(HashMap<String, Object> param) {
        //设置回调对象
        rabbitTemplate.setConfirmCallback(this);
        //构建回调返回的数据
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //发送消息
        rabbitTemplate.convertAndSend(ConstantsConfig.PHONEEXCHANGENAME, ConstantsConfig.SHOPROUTINGKEY, param, correlationData);
    }

    /**
     * 添加购物车
     *
     * @param param
     */
    @Override
    public void saveOrderCart(HashMap<String, Object> param) {
        //设置回调对象
        rabbitTemplate.setConfirmCallback(this);
        //构建回调返回的数据
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //发送消息
        rabbitTemplate.convertAndSend(ConstantsConfig.ORDEREXCHANGENAME, ConstantsConfig.ORDERROUTINGKEY, param, correlationData);
    }


}
