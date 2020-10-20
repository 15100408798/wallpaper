package com.yushang.wallpaper.common.config.rabbitmq.ReceiveMessage;

import com.yushang.wallpaper.common.config.rabbitmq.config.ConstantsConfig;
import com.yushang.wallpaper.common.mapper.ComplainTypeMapper;
import com.yushang.wallpaper.common.utils.log.LoggerUtils;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class OrderReceive {

    @Autowired
    private ComplainTypeMapper complainTypeMapper;

    @RabbitListener(queues = ConstantsConfig.ORDERQUEUE)
    @RabbitHandler
    public void saveOrder1(HashMap<String,Object> param, Message message, Channel channel){
        saveOrder(param,message,channel);
    }

    @RabbitListener(queues = ConstantsConfig.ORDERQUEUE)
    @RabbitHandler
    public void saveOrder2(HashMap<String,Object> param, Message message, Channel channel){
        saveOrder(param,message,channel);
    }


    private void saveOrder(HashMap<String, Object> param, Message message, Channel channel) {
        try {
            complainTypeMapper.saveShoppingCart(param);
            //调用ack告知队列，已经消费完毕，可以删除该消息了
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        } catch (IOException e) {
            e.printStackTrace();
            LoggerUtils.error("用户"+param.get("userId")+"的商品，加入购入车失败",e);
        }
    }



}
