package com.huihui.aligo.feign;

import com.huihui.aligo.dto.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author minghui.y
 * @create 2021-05-29 2:40 下午
 **/
@FeignClient("service-passenger-user")
public interface UserFeignService {

    @GetMapping("/auth/login")
    ResponseResult<String> login(@RequestParam("phoneNumber") String phoneNumber);

}
