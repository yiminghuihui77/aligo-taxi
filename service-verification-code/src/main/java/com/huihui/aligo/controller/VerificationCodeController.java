package com.huihui.aligo.controller;

import com.huihui.aligo.dto.ResponseResult;
import com.huihui.aligo.dto.VerifyCodeResponse;
import com.huihui.aligo.service.VerificationService;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author minghui.y
 * @create 2021-05-23 5:46 下午
 **/
@RestController
@RequestMapping("/verify-code")
public class VerificationCodeController {

    @Autowired
    private VerificationService verificationService;


    @GetMapping("/sayHello")
    public String sayHello() {
        System.out.println("verify-code service visited...");
        return "I am vierify-code service";
    }


    @GetMapping("/generate")
    public ResponseResult<VerifyCodeResponse> getVerificationCode( @RequestParam("identity") int identity,
                                                                   @RequestParam("phoneNumber") String phoneNumber) {
        return verificationService.generateCode( identity, phoneNumber );
    }

    @GetMapping("/verify")
    public ResponseResult<String> verify(@RequestParam("identity") int identity,
                                         @RequestParam("phoneNumber") String phoneNumber,
                                         @RequestParam("verifyCode") String verifyCode) {
        return verificationService.verify( identity, phoneNumber, verifyCode );
    }

}
