package com.huihui.aligo.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * RocketMQ 消费者  死信队列消费
 * 无法启动！！！！报topic命名不符合规范
 * @author minghui.y
 * @create 2021-10-05 10:38 上午
 **/
//@Component
public class OrderTransactionConsumerDld {

    private final String ORDER_CONSUMER_DLD_GROUP = "order-consumer-group—dld";

    private DefaultMQPushConsumer consumer;

    @Resource
    private OrderListenerDld orderListenerDld;


    @PostConstruct
    public void init() throws MQClientException {
        consumer = new DefaultMQPushConsumer(ORDER_CONSUMER_DLD_GROUP);
        consumer.setNamesrvAddr( "127.0.0.1:9876" );
        //消费的主题信息
        consumer.subscribe("%DLQ%order-consumer-group","*");
        //消息监听器(死信)
        consumer.registerMessageListener( orderListenerDld );

        //消费失败2次，就进死信队列  默认16次
        consumer.setMaxReconsumeTimes( 2 );
        consumer.start();
    }

}
