package com.huihui.aligo.controller;

import com.huihui.aligo.dto.ResponseResult;
import com.huihui.aligo.service.VerificationCodeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author minghui.y
 * @create 2021-05-23 9:12 下午
 **/
@RestController
@RequestMapping("/api")
public class VerifyCodeController {


    @Resource
    private VerificationCodeService verificationCodeService;


    @GetMapping("/sendVerifyCode/{identity}/{phoneNumber}")
    public ResponseResult<String> getVerifyCode( @PathVariable("identity") int identity,
                                         @PathVariable("phoneNumber") String phoneNumber) {

        return verificationCodeService.sendVerifyCode( identity, phoneNumber );
    }


    @GetMapping(value = "/verify")
    public ResponseResult<String> verify(@RequestParam("identity") int identity,
                                         @RequestParam("phoneNumber") String phoneNumber,
                                         @RequestParam("verifyCode") String verifyCode) {
        return verificationCodeService.verify( identity, phoneNumber, verifyCode );
    }


}
