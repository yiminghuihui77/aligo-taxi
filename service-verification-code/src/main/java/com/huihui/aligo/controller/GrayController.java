package com.huihui.aligo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author minghui.y
 * @create 2021-06-24 5:34 下午
 **/
@RestController
@RequestMapping("/gray")
public class GrayController {


    @RequestMapping(value = "/getGrayVersion")
    public String serverToServerGray() {
        return  "server to server gray: v2";
    }



}
