package com.huihui.aligo.aspect;

import com.huihui.aligo.threadlocal.ParameterThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author minghui.y
 * @create 2021-06-24 6:46 下午
 **/
//@Component
//@Aspect
@Slf4j
public class VersionAspect {

    @Autowired
    private ParameterThreadLocal parameterThreadLocal;



    @Pointcut("@annotation(com.huihui.aligo.annotation.VersionAnnotation)")
    public void pointCut() {
    }


    @Around( "pointCut()" )
    public Object preHandle( ProceedingJoinPoint joinPoint) throws Throwable {

        //获取请求
        //获取请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        log.info( String.format( "uri: %s >>> method: %s",
                request.getRequestURL().toString(), request.getMethod() ) );

        log.info( String.format( "uri: %s >>> method: %s",
                request.getRequestURL().toString(), request.getMethod() ) );

        String version = request.getHeader( "version" );


        //将version存入threadLocal中
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put( "version", version );

        parameterThreadLocal.set( paramMap );


        //执行目标方法
        return joinPoint.proceed();
    }



}
