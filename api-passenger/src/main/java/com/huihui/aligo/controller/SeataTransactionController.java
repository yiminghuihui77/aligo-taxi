package com.huihui.aligo.controller;

import com.huihui.aligo.annotation.VersionAnnotation;
import com.huihui.aligo.dto.ResponseResult;
import com.huihui.aligo.dto.VerifyCodeResponse;
import com.huihui.aligo.service.VerificationCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于测试seata框架
 * api-passenger -> service-sms -> service-verification-code
 * 模拟获取短信验证码
 * @author minghui.y
 * @create 2021-09-25 10:23 上午
 **/
@RestController
@RequestMapping("/seata")
public class SeataTransactionController {

    @Autowired
    private VerificationCodeService verificationCodeService;



    @VersionAnnotation
    @RequestMapping("/getVerifyCode4Seata")
    public String startTransaction4TM( @RequestParam("identity") int identity, @RequestParam("phoneNumber") String phoneNumber ) {

        ResponseResult<VerifyCodeResponse> codeResult = verificationCodeService.getVerifyCode4Seata( identity, phoneNumber );

        return codeResult.getData().getCode();
    }





}
