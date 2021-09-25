package com.huihui.aligo.service;

import com.huihui.aligo.dto.ResponseResult;
import com.huihui.aligo.dto.VerifyCodeResponse;

/**
 * 验证码服务
 *
 * @author minghui.y
 * @create 2021-05-29 1:05 下午
 **/
public interface VerificationCodeService {

    /**
     * 发送短信验证码
     * @param identity
     * @param phoneNumber
     * @return
     */
    ResponseResult<String> sendVerifyCode(int identity, String phoneNumber);

    /**
     * 验证短信验证码
     * @param identity
     * @param phoneNumber
     * @param verifyCode
     * @return
     */
    ResponseResult<String> verify(int identity, String phoneNumber, String verifyCode);

    /**
     * 获取短信验证码
     * @param identity
     * @param phoneNumber
     * @return
     */
    ResponseResult<VerifyCodeResponse> getVerifyCode4Seata( int identity, String phoneNumber);

}
