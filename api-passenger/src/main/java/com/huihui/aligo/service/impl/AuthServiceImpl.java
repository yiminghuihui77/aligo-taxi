package com.huihui.aligo.service.impl;

import com.huihui.aligo.constant.CommonStatusEnum;
import com.huihui.aligo.dto.ResponseResult;
import com.huihui.aligo.feign.UserFeignService;
import com.huihui.aligo.feign.VerificationCodeFeignService;
import com.huihui.aligo.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author minghui.y
 * @create 2021-05-29 2:33 下午
 **/
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Resource
    private VerificationCodeFeignService verificationCodeFeignService;
    @Resource
    private UserFeignService userFeignService;


    /**
     * 短信验证码登录
     * 1、调用service-verification-code服务验证短信验证码
     * 2、调用service-passenger-user登录
     * @param phoneNumber
     * @param verifyCode
     * @param identity
     * @return
     */
    @Override
    public ResponseResult<String> login( int identity, String phoneNumber, String verifyCode ) {

        //验证短信验证码
        ResponseResult<String> verifyResult = verificationCodeFeignService.verify( identity, phoneNumber, verifyCode );
        if (!(verifyResult != null && verifyResult.getCode() == CommonStatusEnum.SUCCESS.getCode())) {
            return verifyResult;
        }
        log.info( "短信验证码通过验证..." );

        //登录
        ResponseResult<String> loginResult = userFeignService.login( phoneNumber );
        if (loginResult != null && loginResult.getCode() == CommonStatusEnum.SUCCESS.getCode() ) {
            return ResponseResult.success( "短信验证码登录成功" );
        }
        log.info( "登录成功..." );

        return ResponseResult.fail( "短信验证码登录失败" );
    }
}
