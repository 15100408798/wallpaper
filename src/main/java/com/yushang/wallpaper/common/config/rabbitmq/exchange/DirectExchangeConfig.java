package com.yushang.wallpaper.common.config.rabbitmq.exchange;

import com.yushang.wallpaper.common.config.rabbitmq.config.ConstantsConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//生产者消费者模式的配置
@Configuration
public class DirectExchangeConfig {
	
	@Bean
 	public DirectExchange directExchange(){
		return new DirectExchange(ConstantsConfig.LOGExchangeName,true,false);
 	}
	
	@Bean
    public Queue firstQueue() {
      return new Queue(ConstantsConfig.LOGQueueName,true,false,false,null);
    }

    @Bean
	public Binding binding_firstQueue(){
		return BindingBuilder.bind(firstQueue()).to(directExchange()).with(ConstantsConfig.LOGRoutingKey);
	}
 	
}
