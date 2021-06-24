package com.huihui.aligo.threadlocal;

import org.springframework.stereotype.Component;

import java.util.Map;

/**
*  
*  @author minghui.y
*  @create 2021-06-24 6:58 下午
**/
@Component
public class ParameterThreadLocal {

    private ThreadLocal<Map<String, String>> paramThreadLocal = new ThreadLocal<>();


    public Map<String, String> get() {
        return paramThreadLocal.get();
    }


    public void set(Map<String, String> map) {
        paramThreadLocal.set( map );
    }



}
