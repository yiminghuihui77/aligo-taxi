package com.huihui.aligo.filter;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * 使用sentinel实现网关层限流
 *
 * @author minghui.y
 * @create 2021-07-25 9:49 下午
 **/
@Component
@Slf4j
public class SentinelRateFilter extends ZuulFilter {

    public static final String SENTINEL_PROTECT_RESOURCE = "HelloWorld";

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        Entry entry = null;
        try {
            entry = SphU.entry( SENTINEL_PROTECT_RESOURCE );
            log.info( "正常请求..." );
            return null;
        } catch (Exception e) {
            log.error( "sentinel限流，阻塞请求..." );
            requestContext.setSendZuulResponse( false );
            requestContext.setResponseStatusCode( HttpStatus.UNAUTHORIZED.value() );
            requestContext.setResponseBody( "sentinel failed!" );
            return null;
        } finally {
            if (entry != null) {
                entry.exit();
            }
        }

    }
}
