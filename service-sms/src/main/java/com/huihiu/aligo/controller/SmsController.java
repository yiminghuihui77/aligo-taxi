package com.huihiu.aligo.controller;

import com.huihiu.aligo.service.SmsService;
import com.huihui.aligo.dto.ResponseResult;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author minghui.y
 * @create 2021-05-23 5:37 下午
 **/
@RestController
@RequestMapping("/sms")
public class SmsController {

   @Autowired
   private SmsService smsService;

   @RequestMapping(value = "/sendSms", method = RequestMethod.GET)
   public ResponseResult<String> sendSms( @RequestParam("phoneNumber") String phoneNumber,
                                          @RequestParam("verifyCode") String verifyCode) {

      return smsService.sendSms( phoneNumber, verifyCode );
   }


   @GetMapping("/sms-test33")
   public String helloSms() {
      return "sms-test33";
   }

   @GetMapping("/targetHost")
   public String helloTargetHost() {
      return "hello targetHost";
   }

}
