package com.huihui.aligo.feign;

import com.huihui.aligo.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author minghui.y
 * @create 2021-05-29 1:26 下午
 **/
@FeignClient("service-sms")
public interface SmsFeignService {

    @RequestMapping(value = "/sms/sendSms", method = RequestMethod.GET)
    ResponseResult<String> sendSms( @RequestParam("phoneNumber") String phoneNumber,
                                    @RequestParam("verifyCode") String verifyCode);
}
