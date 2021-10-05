package com.huihui.aligo.rocketmq;

import com.alibaba.fastjson.JSONObject;
import com.huihui.aligo.mapper.ApiPassengerMapper;
import com.huihui.aligo.mapper.LocalTransactionMapper;
import com.huihui.aligo.model.ApiPassengerModel;
import com.huihui.aligo.model.LocalTransactionEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 业务处理器
 *
 * @author minghui.y
 * @create 2021-10-04 10:09 下午
 **/
@Component
@Slf4j
public class OrderBusinessHandler {

    @Autowired
    private OrderTransactionProducer producer;
    @Resource
    private LocalTransactionMapper localTransactionMapper;
    @Resource
    private ApiPassengerMapper apiPassengerMapper;

    /**
     * executeLocalTransaction的核心逻辑
     * 处理业务逻辑 & 插入本地事件记录
     * @param model
     * @param message
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public String createOrder(ApiPassengerModel model, Message message) {
        //业务逻辑：往t_api_passenger表插入一条数据
        apiPassengerMapper.insert( model );

        //往本地事件表插入一条数据t_rocket_transaction_event
        LocalTransactionEvent event = new LocalTransactionEvent();
        event.setTransactionId( message.getTransactionId() );
        event.setMessage( JSONObject.toJSONString( message ) );
        localTransactionMapper.insert( event );

        return "success";
    }


    /**
     * 提供给前端的接口逻辑
     * @param model
     * @return
     */
    public String createOrder( ApiPassengerModel model ) throws MQClientException {
        TransactionSendResult sendResult = producer.send( JSONObject.toJSONString( model ), "order_topic" );
        return sendResult.getTransactionId();
    }

}
