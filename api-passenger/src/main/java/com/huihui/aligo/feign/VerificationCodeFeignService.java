package com.huihui.aligo.feign;

import com.huihui.aligo.dto.ResponseResult;
import com.huihui.aligo.dto.VerifyCodeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author minghui.y
 * @create 2021-05-23 9:04 下午
 **/
@FeignClient("service-verification-code")
public interface VerificationCodeFeignService {

    @GetMapping("/verify-code/generate")
    ResponseResult<VerifyCodeResponse> getVerificationCode( @RequestParam("identity") int identity,
                                                            @RequestParam("phoneNumber") String phoneNumber );

    @GetMapping("/verify-code/generate4Seata")
    ResponseResult<VerifyCodeResponse> getVerificationCode4Seata( @RequestParam("identity") int identity,
                                                            @RequestParam("phoneNumber") String phoneNumber );

    @GetMapping("/verify-code/verify")
    ResponseResult<String> verify(@RequestParam("identity") int identity,
                                  @RequestParam("phoneNumber") String phoneNumber,
                                  @RequestParam("verifyCode")String verifyCode);



    @GetMapping("/gray/getGrayVersion")
    String serverToServerGray();
}
