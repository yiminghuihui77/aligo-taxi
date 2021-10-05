package com.huihui.aligo.rocketmq;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author minghui.y
 * @create 2021-10-04 5:15 下午
 **/
@Component
public class OrderTransactionProducer {

    private final String PRODUCER_GROUP = "order_trans_group";

    private TransactionMQProducer producer;

    /**
     * 用于执行本地事务和事务状态回查的监听器
     */
    @Autowired
    private OrderTransactionListener orderTransactionListener;


    ThreadPoolExecutor executor = new ThreadPoolExecutor( 5,
            5,
            30,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>( 50 ) );


    @PostConstruct
    private void init() {
        producer= new TransactionMQProducer(PRODUCER_GROUP);
        //Name Server的地址
        producer.setNamesrvAddr( "127.0.0.1:9876" );
        //
        producer.setVipChannelEnabled( false );
        producer.setSendMsgTimeout( Integer.MAX_VALUE );
        producer.setExecutorService( executor );
        //自定义的
        producer.setTransactionListener( orderTransactionListener );
        this.start();
    }

    private void start() {
        try {
            producer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送消息
     * @param data
     * @param topic
     * @return
     * @throws MQClientException
     */
    public TransactionSendResult send(String data, String topic) throws MQClientException {
        Message message = new Message(topic, data.getBytes());
        return producer.sendMessageInTransaction( message, null );
    }

}
