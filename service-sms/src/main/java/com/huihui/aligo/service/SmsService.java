package com.huihui.aligo.service;

import com.huihui.aligo.dto.ResponseResult;

/**
 * @author minghui.y
 * @create 2021-05-29 1:22 下午
 **/
public interface SmsService {

    /**
     * 调用第三方短信服务发送短信验证码
     * @param phoneNumber
     * @param verifyCode
     * @return
     */
    ResponseResult<String> sendSms(String phoneNumber, String verifyCode);


    ResponseResult<String> sendSms4Seata(String phoneNumber, String verifyCode);
}
