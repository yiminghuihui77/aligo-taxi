package com.huihui.aligo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.huihui.aligo.filter.SentinelRateFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;

/**
 * @author minghui.y
 * @create 2021-07-03 8:59 上午
 **/
@RestController
@RequestMapping("/helloZuul")
public class HelloController {


    @GetMapping("/sayHello")
    String sayHello() {
        System.out.println("我是目标资源...");
        return "hello zuul.";
    }


    @GetMapping("/saySentinel")
    @SentinelResource(value = SentinelRateFilter.SENTINEL_PROTECT_RESOURCE, blockHandler = "failSentinel")
    public String saySentinel() {
        System.out.println("saySentinel接口被访问...");
        return "saySentinel";
    }

    /**
     * 限流降级方法
     * 返回值需要和业务接口一致
     * 必须有参数:BlockException
     * @param ex
     * @return
     */
    public String failSentinel( BlockException ex ) {
        System.out.println("failSentinel限流接口被执行...");
        ex.printStackTrace();
        return "failSentinel...";
    }

}
