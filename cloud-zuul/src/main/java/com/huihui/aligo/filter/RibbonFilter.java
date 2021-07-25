package com.huihui.aligo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 解决的问题：
 * 老项目配置的请求地址是sms-test3
 * 但是服务提供者service-sms的接口地址是sms-test33
 * @author minghui.y
 * @create 2021-07-04 6:46 下午
 **/
//@Component
@Slf4j
public class RibbonFilter extends ZuulFilter {

    @Override
    public String filterType() {
        return FilterConstants.ROUTE_TYPE;
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

        if (requestURI.contains( "/sms-test3" )) {
            //设置分发到指定serviceId
            requestContext.set( FilterConstants.SERVICE_ID_KEY, "service-sms" );
            //设置指定url
            requestContext.set( FilterConstants.REQUEST_URI_KEY, "/sms/sms-test33" );
        }


        return null;
    }
}
