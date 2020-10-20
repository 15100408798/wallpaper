package com.yushang.wallpaper.common.config.rabbitmq.exchange;

import com.yushang.wallpaper.common.config.rabbitmq.config.ConstantsConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PhoneExchange {
	
	@Bean
	public DirectExchange phoneDirectExchange(){
		return new DirectExchange(ConstantsConfig.PHONEEXCHANGENAME);
	}
	
	@Bean
	public Queue phoneQueue(){
		return new Queue(ConstantsConfig.PHONEQUEUE);
	}

	@Bean
	public Queue shopQueue(){
		return new Queue(ConstantsConfig.SHOPQUEUE);
	}

	@Bean
	public Binding phoneBinding(){
		return BindingBuilder.bind(phoneQueue()).to(phoneDirectExchange()).with(ConstantsConfig.PHONEROUTINGKEY);
	}

	@Bean
	public Binding shopBinding(){
		return BindingBuilder.bind(shopQueue()).to(phoneDirectExchange()).with(ConstantsConfig.SHOPROUTINGKEY);
	}
	
}
