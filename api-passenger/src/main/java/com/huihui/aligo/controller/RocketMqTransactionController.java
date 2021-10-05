package com.huihui.aligo.controller;

import com.huihui.aligo.model.ApiPassengerModel;
import com.huihui.aligo.rocketmq.OrderBusinessHandler;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 使用RocketMQ事务消息实现分布式事务
 *
 * @author minghui.y
 * @create 2021-10-04 5:10 下午
 **/
@RestController
@RequestMapping("/rocketmq/")
public class RocketMqTransactionController {
    
    @Autowired
    private OrderBusinessHandler orderBusinessHandler;


    @RequestMapping("/startBusiness")
    public String startRocketMqTransaction( @RequestParam("identity") Integer identity, 
                                            @RequestParam("phoneNumber") String phoneNumber) throws MQClientException {

        ApiPassengerModel model = new ApiPassengerModel();
        model.setIdentity( identity );
        model.setPhoneNumber( phoneNumber );
        model.setCreateTime( new Date() );
        //仅发消息
        return orderBusinessHandler.createOrder(model);
    }

}
