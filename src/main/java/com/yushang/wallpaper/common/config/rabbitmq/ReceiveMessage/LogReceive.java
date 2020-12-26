package com.yushang.wallpaper.common.config.rabbitmq.ReceiveMessage;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.yushang.wallpaper.common.config.rabbitmq.config.ConstantsConfig;
import com.yushang.wallpaper.common.pojo.log.TbLog;
import com.yushang.wallpaper.common.utils.log.LoggerUtils;
import com.yushang.wallpaper.layer.router.log.LogService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description: 接收消息
 */
@Component
public class LogReceive {

    @Autowired
    private LogService logService;

    @RabbitListener(queues = ConstantsConfig.LOGQueueName)
    @RabbitHandler
    public void receiveMessage1(Message message, Channel channel) {
        insertLog(message, channel);
    }

    @RabbitListener(queues = ConstantsConfig.LOGQueueName)
    @RabbitHandler
    public void receiveMessage2(Message message, Channel channel) {
        insertLog(message, channel);
    }

    @Transactional
    public void insertLog(Message message, Channel channel) {
        try {
            String tbLogMessage = new String(message.getBody());
            TbLog tbLog = JSONObject.parseObject(tbLogMessage, TbLog.class);
            logService.insertLog(tbLog);
            // 告诉队列可以删除这条消息了
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            LoggerUtils.error("向数据库插入日志出错", e);
        }
    }

}
