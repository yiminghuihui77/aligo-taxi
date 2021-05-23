package com.huihiu.aligo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author minghui.y
 * @create 2021-05-23 5:37 下午
 **/
@RestController
@RequestMapping("/sms")
public class SmsController {

    @GetMapping("/sayHello")
    public String sayHello() {
        return "Sms hello";
    }



}
