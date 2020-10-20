package com.yushang.wallpaper.common.config.rabbitmq.config;

import com.yushang.wallpaper.common.config.rabbitmq.SendMessage.SendMessageService;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Autowired
    private SendMessageService sendMessageService;
    
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(jackJson2MessageConvert());
        rabbitTemplate.setConfirmCallback(sendMessageService);
        return rabbitTemplate;
    }

    private MessageConverter jackJson2MessageConvert() {
        return new Jackson2JsonMessageConverter();
    }


}
