package com.yushang.wallpaper.common.config.rabbitmq.config;

public class ConstantsConfig {
	
	/**  =============================== 日志==========================  */
	
    public static final String LOGExchangeName = "log.exchange";
    public static final String LOGQueueName = "log.queue";
    public static final String LOGRoutingKey = "log.routing.key";
    
    
    
    
    /** =============================== 手机端发送短信==========================  */
	
    public static final String PHONEEXCHANGENAME = "phone.exchange";
    public static final String PHONEQUEUE = "phone.queue";
    public static final String PHONEROUTINGKEY = "phone.routing.key";

    public static final String SHOPQUEUE = "shop.queue";
    public static final String SHOPROUTINGKEY = "shop.routing.key";

    /**  =============================购物车交换机======================   */

    public static final String ORDEREXCHANGENAME = "order.exchange";
    public static final String ORDERQUEUE = "order.queue";
    public static final String ORDERROUTINGKEY = "order.routing.key";


    /**  =============================提交订单交换机======================   */
    public static final String SAVEORDERQUEUE = "save.order.queue";
    public static final String SAVEORDERROUTINGKEY = "save.order.routing.key";

    
}
