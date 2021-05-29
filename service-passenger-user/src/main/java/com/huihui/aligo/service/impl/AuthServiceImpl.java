package com.huihui.aligo.service.impl;

import com.huihui.aligo.constant.RedisKeyPrefixConstant;
import com.huihui.aligo.dto.ResponseResult;
import com.huihui.aligo.service.AuthService;
import com.huihui.aligo.util.JwtUtil;
import com.huihui.aligo.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author minghui.y
 * @create 2021-05-29 2:28 下午
 **/
@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Resource
    private RedisUtil redisUtil;


    @Override
    public ResponseResult<String> login( String phoneNumber ) {
        //入库 TODO

        long userId = new Random().nextLong();


        //根据userId,生成token
        String token = JwtUtil.createToken(String.valueOf( userId ), new Date());
        log.info( "为手机号生成token:{}", token );
        //token存入Redis中，默认过期时间30天
        redisUtil.set( RedisKeyPrefixConstant.PASSENGER_LOGIN_TOKEN_APP_KEY_PRE + userId, token, 30, TimeUnit.DAYS );

        return ResponseResult.success( token );
    }

    @Override
    public ResponseResult<String> logout( String phoneNumber ) {
        return null;
    }
}
