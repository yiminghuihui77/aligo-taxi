package com.huihui.aligo.rocketmq;

import com.alibaba.fastjson.JSONObject;
import com.huihui.aligo.model.ApiPassengerModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * rocketMQ的消费核心逻辑
 * @author minghui.y
 * @create 2021-10-05 10:45 上午
 **/
@Slf4j
@Component
public class OrderListenerDld implements MessageListenerConcurrently {



    @Override
    public ConsumeConcurrentlyStatus consumeMessage( List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext ) {
       log.info( "消费端监听到消息..." );

       try {

           for (MessageExt messageExt : list) {
               //获取消息内容
               ApiPassengerModel model = JSONObject.parseObject( messageExt.getBody(), ApiPassengerModel.class );

               //死信队列处理
               System.out.println("死信队列处理消息：" + JSONObject.toJSONString( model ));
           }

           //返回消费成功状态
           return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;

       } catch (Exception e) {
           log.error( "消息消费失败： {}", e );
           //返回稍后重新消费状态
           return ConsumeConcurrentlyStatus.RECONSUME_LATER;
       }

    }
}
