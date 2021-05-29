package com.huihui.aligo.controller;

import com.huihui.aligo.dto.ResponseResult;
import com.huihui.aligo.service.AuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author minghui.y
 * @create 2021-05-29 2:30 下午
 **/
@RestController
@RequestMapping("/api")
public class AuthController {

    @Resource
    private AuthService  authService;

    @GetMapping("/login")
    public ResponseResult<String> login(@RequestParam("identity") int identity, @RequestParam("phoneNumber") String phoneNumber,
                                         @RequestParam("verifyCode") String verifyCode) {
        return authService.login( identity, phoneNumber, verifyCode );
    }

}
