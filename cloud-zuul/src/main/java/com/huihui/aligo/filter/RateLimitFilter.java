package com.huihui.aligo.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 限流过滤器
 * 算法：令牌桶
 * 使用Guava的【RateLimiter】做限流
 * @author minghui.y
 * @create 2021-07-25 12:41 下午
 **/
//@Component
@Slf4j
public class RateLimitFilter extends ZuulFilter {

    /**
     * 每秒创建一个令牌
     */
    private static final RateLimiter RATE_LIMITER = RateLimiter.create( 1 );

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
        HttpServletResponse response = requestContext.getResponse();

        if (RATE_LIMITER.tryAcquire(500, TimeUnit.MICROSECONDS)) {
            //指定时间内尝试获取令牌
             log.info( Thread.currentThread().getName() + "线程获取到令牌，开始执行..." );
             return null;
        } else {
            //为获取到令牌，则不往后执行
            requestContext.setSendZuulResponse( false );
            requestContext.setResponseStatusCode( HttpStatus.FORBIDDEN.value() );
//            requestContext.setResponseBody( "网关已经限流..." );
            try {
                response.getWriter().write( Thread.currentThread().getName() + "线程已经被限流...");
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}
