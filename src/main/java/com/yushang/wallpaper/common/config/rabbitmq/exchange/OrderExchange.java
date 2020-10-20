package com.yushang.wallpaper.common.config.rabbitmq.exchange;

import com.yushang.wallpaper.common.config.rabbitmq.config.ConstantsConfig;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 订单交换机
 */
@Configuration
public class OrderExchange {

    /**  ======================= 购物车交换机  ============================*/

    @Bean
    public DirectExchange orderDirectExchange(){
        return new DirectExchange(ConstantsConfig.ORDEREXCHANGENAME);
    }

    @Bean
    public Queue orderQueue(){
        return new Queue(ConstantsConfig.ORDERQUEUE);
    }

    @Bean
    public Binding bindingOrder(){
        return BindingBuilder.bind(orderQueue()).to(orderDirectExchange()).with(ConstantsConfig.ORDERROUTINGKEY);
    }


    /**  ======================= 订单交换机  ============================*/

    @Bean
    public Queue saveOrderQueue(){
        return new Queue(ConstantsConfig.SAVEORDERQUEUE,true,false,false,null);
    }

    @Bean
    public Binding bindingSaveOrder(){
        return BindingBuilder.bind(saveOrderQueue()).to(orderDirectExchange()).with(ConstantsConfig.SAVEORDERROUTINGKEY);
    }

    /** ==========================  定义死信交换机 ====================================== */

    @Bean
    public DirectExchange deadLetterExchange(){
        return new DirectExchange("order.dead.exchange",true,false);
    }

    @Bean
    public Queue deadLetterQueue(){
        return new Queue("order.dead.queue",true,false,false,null);
    }

    @Bean
    public Binding deadLetterBinding(){
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with("saveDeadLetterOrder");
    }
}
