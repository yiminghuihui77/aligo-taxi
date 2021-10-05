package com.huihui.aligo.rocketmq;

import com.alibaba.fastjson.JSONObject;
import com.huihui.aligo.mapper.VerifyCodeMapper;
import com.huihui.aligo.model.ApiPassengerModel;
import com.huihui.aligo.model.VerifyCodeModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 消费端的业务处理器
 *
 * @author minghui.y
 * @create 2021-10-05 10:55 上午
 **/
@Component
@Slf4j
public class OrderConsumerHandler {

    @Resource
    private VerifyCodeMapper verifyCodeMapper;



    @Transactional(rollbackFor = Exception.class)
    public void consumerOrder( ApiPassengerModel model ) {
        log.info( "处理订单信息：{}", JSONObject.toJSONString( model ) );

        //往数据库插入数据
        VerifyCodeModel verifyCodeModel = new VerifyCodeModel();
        verifyCodeModel.setIdentity( model.getIdentity() );
        verifyCodeModel.setPhoneNumber( model.getPhoneNumber() );
        String code = String.valueOf((int)((Math.random()*9+1)*Math.pow(10,5)));
        verifyCodeModel.setVerifyCode( code );
        verifyCodeModel.setCreateTime( new Date() );

        verifyCodeMapper.insert( verifyCodeModel );

        //模拟业务处理异常
        int i = 1 / 0;

    }



}
