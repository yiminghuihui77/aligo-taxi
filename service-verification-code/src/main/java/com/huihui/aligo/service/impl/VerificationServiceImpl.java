package com.huihui.aligo.service.impl;

import com.huihui.aligo.dto.ResponseResult;
import com.huihui.aligo.dto.VerifyCodeResponse;
import com.huihui.aligo.service.VerificationService;
import org.springframework.stereotype.Service;

/**
 * @author minghui.y
 * @create 2021-05-23 6:13 下午
 **/
@Service
public class VerificationServiceImpl implements VerificationService {



    @Override
    public ResponseResult<VerifyCodeResponse> generateCode( int identity, String phoneNumber ) {
       //安全监测：不能无限制发短信：同一个手机号1分钟不能超过3次等


        //生产6位验证码
        String code = String.valueOf((int)((Math.random()*9+1)*Math.pow(10,5)));
        //存储到redis中设置过期时间

        return ResponseResult.success( new VerifyCodeResponse(code) );
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
