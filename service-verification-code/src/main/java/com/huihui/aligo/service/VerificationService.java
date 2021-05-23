package com.huihui.aligo.service;

import com.huihui.aligo.dto.ResponseResult;
import com.huihui.aligo.dto.VerifyCodeResponse;

/**
 * @author minghui.y
 * @create 2021-05-23 6:13 下午
 **/
public interface VerificationService {

    ResponseResult<VerifyCodeResponse> generateCode( int identity, String phoneNumber);
}
