package com.huihui.aligo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author minghui.y
 * @create 2021-07-03 8:59 上午
 **/
@RestController
@RequestMapping("/helloZuul")
public class HelloController {


    @GetMapping("/sayHello")
    String sayHello() {
        return "hello zuul.";
    }

}
