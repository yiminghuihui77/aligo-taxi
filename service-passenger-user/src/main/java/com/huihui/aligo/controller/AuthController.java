package com.huihui.aligo.controller;

import com.huihui.aligo.dto.ResponseResult;
import com.huihui.aligo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author minghui.y
 * @create 2021-05-29 2:23 下午
 **/
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @GetMapping("/login")
    public ResponseResult<String> login(@RequestParam("phoneNumber") String phoneNumber) {
        return authService.login( phoneNumber );
    }


}
