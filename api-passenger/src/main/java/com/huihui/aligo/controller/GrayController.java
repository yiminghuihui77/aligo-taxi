package com.huihui.aligo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 灰度测试控制器
 * @author minghui.y
 * @create 2021-06-23 3:33 下午
 **/
@RestController
@RequestMapping("/gray")
public class GrayController {


    @RequestMapping(value = "/helloGray", method = RequestMethod.GET)
    public String helloGray() {
        return "this is v1 version!";
    }


}
