package com.huihui.aligo.service;

import com.huihui.aligo.dto.ResponseResult;

/**
 * 登录认证服务
 *
 * @author minghui.y
 * @create 2021-05-29 2:27 下午
 **/
public interface AuthService {

    /**
     * 登录
     * @param phoneNumber
     * @return
     */
    ResponseResult<String> login(String phoneNumber);

    /**
     * 注销
     * @param phoneNumber
     * @return
     */
    ResponseResult<String> logout(String phoneNumber);

}
