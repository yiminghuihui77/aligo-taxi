package com.huihui.aligo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URL;

/**
 * @author minghui.y
 * @create 2021-07-04 8:57 下午
 **/
@Slf4j
//@Component
public class HostFilter extends ZuulFilter {
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

    @SneakyThrows
    @Override
    public Object run() throws ZuulException {

        //获取请求
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        log.info( String.format( "uri: %s >>> method: %s",
                request.getRequestURL().toString(), request.getMethod() ) );

        //请求url
        String requestURI = request.getRequestURI();

        if (requestURI.contains( "/targetHost" )) {
            //指定目标url：http://localhost:8090/sms/targetHost
            requestContext.setRouteHost( new URI( "http://localhost:8090/sms" ).toURL() );
        }


        return null;
    }
}
