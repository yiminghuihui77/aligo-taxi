package com.huihui.aligo.service.impl;

import com.huihui.aligo.dto.ResponseResult;
import com.huihui.aligo.dto.VerifyCodeResponse;
import com.huihui.aligo.service.VerificationService;
import com.huihui.aligo.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author minghui.y
 * @create 2021-05-23 6:13 下午
 **/
@Service
@Slf4j
public class VerificationServiceImpl implements VerificationService {


    @Autowired
    private RedisUtil redisUtil;

    /**
     * 生成验证码
     * @param identity
     * @param phoneNumber
     * @return
     */
    @Override
    public ResponseResult<VerifyCodeResponse> generateCode( int identity, String phoneNumber ) {
       //安全监测：不能无限制发短信：同一个手机号1分钟不能超过3次等


        //生产6位验证码
        String code = String.valueOf((int)((Math.random()*9+1)*Math.pow(10,5)));
        //存储到redis中设置过期时间：2分钟
        redisUtil.set( phoneNumber + "-" + identity, code, 60 * 2 );

        log.info( "为手机号：{} 生成验证码：{}", phoneNumber, code );

        return ResponseResult.success( new VerifyCodeResponse(code) );
    }

    /**
     * 验证验证码
     * @param identity
     * @param phoneNumber
     * @param verifyCode
     * @return
     */
    @Override
    public ResponseResult<String> verify( int identity, String phoneNumber, String verifyCode ) {
        String key = phoneNumber + "-" + identity;

        //从Redis中查询
        String codeInRedis = (String) redisUtil.get( key );

        log.info( "验证手机号：{} 的验证码：{}，redis中的验证码:{}", phoneNumber, verifyCode, codeInRedis );

        if (verifyCode.equals( codeInRedis )) {
            return ResponseResult.success( "验证码正确" );
        }

        return ResponseResult.fail( "验证码错误或已失效" );
    }


    public static void main( String[] args ) {
        //方式一
        String code1 = (Math.random() + "").substring( 2, 8 );
        //方式二：更快，对数字操作相比于字符串更快
        String code2 = String.valueOf( ((int)((Math.random() * 9 + 1) * Math.pow( 10, 5 ))) );

        System.out.println(code1);
        System.out.println(code2);
    }


}
