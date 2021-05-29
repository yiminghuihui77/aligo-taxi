package com.huihui.aligo.controller;

import com.huihui.aligo.dto.ResponseResult;
import com.huihui.aligo.dto.VerifyCodeResponse;
import com.huihui.aligo.service.VerificationCodeFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author minghui.y
 * @create 2021-05-23 9:12 下午
 **/
@RestController
@RequestMapping("/api")
public class SendVerifyCodeController {

    @Resource
    private VerificationCodeFeignService verificationCodeFeignService;


    @GetMapping("/getVerifyCode/{identity}/{phoneNumber}")
    public String getVerifyCode( @PathVariable("identity") int identity,
                                 @PathVariable("phoneNumber") String phoneNumber) {

        ResponseResult<VerifyCodeResponse> result = verificationCodeFeignService.getVerificationCode( identity, phoneNumber );

        return result.getData().toString();
    }


}
