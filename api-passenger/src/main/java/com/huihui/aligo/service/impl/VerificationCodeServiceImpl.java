package com.huihui.aligo.service.impl;

import com.huihui.aligo.constant.CommonStatusEnum;
import com.huihui.aligo.dto.ResponseResult;
import com.huihui.aligo.dto.VerifyCodeResponse;
import com.huihui.aligo.feign.SmsFeignService;
import com.huihui.aligo.feign.VerificationCodeFeignService;
import com.huihui.aligo.mapper.ApiPassengerMapper;
import com.huihui.aligo.model.ApiPassengerModel;
import com.huihui.aligo.service.VerificationCodeService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author minghui.y
 * @create 2021-05-29 1:08 下午
 **/
@Service
public class VerificationCodeServiceImpl implements VerificationCodeService {

    @Resource
    private VerificationCodeFeignService verificationCodeFeignService;
    @Resource
    private SmsFeignService smsFeignService;
    @Resource
    private ApiPassengerMapper apiPassengerMapper;

    /**
     * 调用service-verification-code服务生成验证码(存到Redis中，过期时间2分钟)
     * 调用service-sms服务向目标手机号发送短信验证码
     * @param identity
     * @param phoneNumber
     * @return
     */
    @Override
    public ResponseResult<String> sendVerifyCode( int identity, String phoneNumber ) {

        //生成验证码
        ResponseResult<VerifyCodeResponse> result = verificationCodeFeignService.getVerificationCode( identity, phoneNumber );

        if (!(result!= null && result.getCode() == CommonStatusEnum.SUCCESS.getCode())) {
            return ResponseResult.fail( "生成短信验证码异常" );
        }

        //获取验证码
        String verifyCode = result.getData().getCode();

        //发送短信
        ResponseResult<String> smsResult = smsFeignService.sendSms( phoneNumber, verifyCode );
        if (smsResult != null && smsResult.getCode() == CommonStatusEnum.SUCCESS.getCode()) {
            return ResponseResult.fail( "发送短信验证码成功" );
        }

        return ResponseResult.success( "发送短信验证码异常" );
    }

    /**
     * 调用service-verification-code服务验证短信验证码的正确性
     * @param identity
     * @param phoneNumber
     * @param verifyCode
     * @return
     */
    @Override
    public ResponseResult<String> verify( int identity, String phoneNumber, String verifyCode ) {

        return verificationCodeFeignService.verify( identity, phoneNumber, verifyCode );
    }

    @Override
    @GlobalTransactional(rollbackFor = Exception.class, timeoutMills = 120 * 1000)
    public ResponseResult<VerifyCodeResponse> getVerifyCode4Seata( int identity, String phoneNumber ) {

        //执行本地DB
        ApiPassengerModel model = new ApiPassengerModel();
        model.setIdentity( identity );
        model.setPhoneNumber( phoneNumber );
        model.setCreateTime( new Date() );
        apiPassengerMapper.insert( model );

        //调用service-verification-code服务生成验证码
        return verificationCodeFeignService.getVerificationCode4Seata( identity, phoneNumber );
    }
}
