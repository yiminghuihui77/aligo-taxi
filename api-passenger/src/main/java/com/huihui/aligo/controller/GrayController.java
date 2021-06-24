package com.huihui.aligo.controller;

import com.huihui.aligo.annotation.VersionAnnotation;
import com.huihui.aligo.feign.VerificationCodeFeignService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 灰度测试控制器
 * @author minghui.y
 * @create 2021-06-23 3:33 下午
 **/
@RestController
@RequestMapping("/gray")
public class GrayController {
    @Resource
    private VerificationCodeFeignService verificationCodeFeignService;


    @RequestMapping(value = "/helloGray", method = RequestMethod.GET)
    public String helloGray() {
        return "this is v1 version!";
    }


    /**
     * api-passenger ->本地ribbon  -> service-verification-code
     * @return
     */
    @VersionAnnotation
    @RequestMapping(value = "/getServerToServerGrayVersion", method = RequestMethod.GET)
    public String getServerToServerGrayVersion() {
        return verificationCodeFeignService.serverToServerGray();
    }


}
