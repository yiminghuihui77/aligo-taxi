package com.huihui.aligo.service;

import com.huihui.aligo.dto.ResponseResult;

/**
 * @author minghui.y
 * @create 2021-05-29 2:32 下午
 **/
public interface AuthService {

    /**
     * 短信验证码登录
     * @param identity
     * @param phoneNumber
     * @param verifyCode
     * @return
     */
    ResponseResult<String> login( int identity, String phoneNumber, String verifyCode);
}
