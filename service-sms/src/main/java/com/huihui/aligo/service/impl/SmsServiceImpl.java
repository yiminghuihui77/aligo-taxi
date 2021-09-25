package com.huihui.aligo.service.impl;

import com.huihui.aligo.mapper.SmsServiceMapper;
import com.huihui.aligo.model.SmsServiceModel;
import com.huihui.aligo.service.SmsService;
import com.huihui.aligo.dto.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author minghui.y
 * @create 2021-05-29 1:23 下午
 **/
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {

    @Resource
    private SmsServiceMapper smsServiceMapper;

    @Override
    public ResponseResult<String> sendSms( String phoneNumber, String verifyCode ) {
        //TODO 调用第三方短信服务，发送短信验证码

        //可以向钉钉群发送验证码

        return ResponseResult.success( "短信发送成功" );
    }




    @Override
    public ResponseResult<String> sendSms4Seata( String phoneNumber, String verifyCode ) {

        //本地DB操作
        SmsServiceModel model = new SmsServiceModel();
        model.setPhoneNumber( phoneNumber );
        model.setVerifyCode( verifyCode );
        model.setCreateTime( new Date() );
        smsServiceMapper.insert( model );

        log.info( "service-sms 发送短信验证码成功，phoneNumber:{}，verifyCode:{}", phoneNumber, verifyCode );

        return ResponseResult.success( "短信发送成功" );
    }
}
