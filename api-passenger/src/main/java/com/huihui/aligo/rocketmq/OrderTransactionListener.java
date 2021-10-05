package com.huihui.aligo.rocketmq;

import com.alibaba.fastjson.JSONObject;
import com.huihui.aligo.mapper.LocalTransactionMapper;
import com.huihui.aligo.model.ApiPassengerModel;
import com.huihui.aligo.model.LocalTransactionEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author minghui.y
 * @create 2021-10-04 5:28 下午
 **/
@Service
@Slf4j
public class OrderTransactionListener implements TransactionListener {

    @Autowired
    private OrderBusinessHandler orderBusinessHandler;

    @Resource
    private LocalTransactionMapper localTransactionMapper;


    /**
     * 发送half message 返回send ok 之后调用的方法
     * 执行本地事务
     * 业务逻辑
     * @param message
     * @param o
     * @return
     */
    @Override
    public LocalTransactionState executeLocalTransaction( Message message, Object o ) {
        log.info( "开始执行本地事务, message:{}", JSONObject.toJSONString( message ) );
        LocalTransactionState state;
        try {
            //获取消息内容
            String body = new String(message.getBody());
            ApiPassengerModel model = JSONObject.parseObject( body, ApiPassengerModel.class );
            //处理业务逻辑（事务中）插入本地事务表一条记录
            orderBusinessHandler.createOrder( model, message );

            //设定消息状态为 UNKNOW（不直接是COMMIT_MESSAGE，在checkLocalTransaction方法中返回真正的状态）
            //返回COMMIT_MESSAGE之后，消息就会被消费者消费
            state = LocalTransactionState.UNKNOW;

            log.info( "本地事务已经提交， message.transactionId:{}", message.getTransactionId() );

        } catch (Exception e) {
            log.error( "执行本地事务失败:{}", JSONObject.toJSONString( message ));
            //回滚消息
            state = LocalTransactionState.ROLLBACK_MESSAGE;
        }
        return state;
    }

    /**
     * 反查消息状态
     * LocalTransactionState.UNKNOW 状态下调该方法
     * @param messageExt
     * @return
     */
    @Override
    public LocalTransactionState checkLocalTransaction( MessageExt messageExt ) {
        log.info( "反查本地事务状态，message.transactionId:{}", messageExt.getTransactionId() );

        String transactionId = messageExt.getTransactionId();

        //根据transactionId，从本地事务表中查询事务记录，存在则返回COMMIT_MESSAGE，否则UNKNOW
        LocalTransactionEvent event = localTransactionMapper.getByTransactionId( transactionId );

        return Objects.isNull(event) ? LocalTransactionState.UNKNOW : LocalTransactionState.COMMIT_MESSAGE;
    }
}
