package com.huihiu.aligo.service.impl;

import com.huihiu.aligo.service.SmsService;
import com.huihui.aligo.dto.ResponseResult;
import org.springframework.stereotype.Service;

/**
 * @author minghui.y
 * @create 2021-05-29 1:23 下午
 **/
@Service
public class SmsServiceImpl implements SmsService {


    @Override
    public ResponseResult<String> sendSms( String phoneNumber, String verifyCode ) {
        //TODO 调用第三方短信服务，发送短信验证码

        //可以向钉钉群发送验证码

        return ResponseResult.success( "短信发送成功" );
    }
}
