package com.huihui.aligo.rocketmq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * RocketMQ 消费者
 * @author minghui.y
 * @create 2021-10-05 10:38 上午
 **/
@Component
public class OrderTransactionConsumer {

    private final String ORDER_CONSUMER_GROUP = "order-consumer-group";

    private DefaultMQPushConsumer consumer;

    @Autowired
    private OrderListener orderListener;


    @PostConstruct
    public void init() throws MQClientException {
        consumer = new DefaultMQPushConsumer(ORDER_CONSUMER_GROUP);
        consumer.setNamesrvAddr( "127.0.0.1:9876" );
        consumer.setVipChannelEnabled( false );
        //消费的主题信息
        consumer.subscribe( "order_topic", "*" );
        //消息监听器
        consumer.registerMessageListener( orderListener );

        //消费失败2次，就进死信队列  默认16次
        consumer.setMaxReconsumeTimes( 2 );
        consumer.start();
    }

}
