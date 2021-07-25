package com.huihui.aligo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

/**
 * 验证一个问题：
 *  请求地址：zuulHost:zuulPort/xxx是不会走到此"过滤器"的，实际上没走到ZuulServlet
 *  ZuulFilter并非传统意义上的javax.servlet.Filter，而只是ZuulServlet流程中的一个执行环节
 * @author minghui.y
 * @create 2021-07-04 9:27 下午
 **/
//@Component
@Slf4j
public class PreFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        //获取请求
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        log.info( String.format( "uri: %s >>> method: %s",
                request.getRequestURL().toString(), request.getMethod() ) );

        //请求url
        String requestURI = request.getRequestURI();

        if (requestURI.contains( "preFilter" )) {
            System.out.println("pre filter 能拦截所有请求？");
        }


        return null;
    }
}
