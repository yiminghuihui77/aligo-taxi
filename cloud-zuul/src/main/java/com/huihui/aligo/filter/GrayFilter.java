package com.huihui.aligo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jmnarloch.spring.cloud.ribbon.support.RibbonFilterContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 灰度过滤器
 *
 * @author minghui.y
 * @create 2021-06-23 3:08 下午
 **/
@Component
@Slf4j
public class GrayFilter extends ZuulFilter {

    private static final Map<String, String> userVersionMap = new HashMap<>(2);

    static {
        userVersionMap.put( "1", "v1" );
        userVersionMap.put( "2", "v2" );
    }


    @Override
    public String filterType() {
        //路由类型
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

        //请求头中获取userId
        String userId = request.getHeader( "userId" );

        //TODO 查询数据库，获取当前用户可范访问的版本号
        String version = userVersionMap.get( userId );

        //当前请求会路由到指定version的服务！
        RibbonFilterContextHolder.getCurrentContext().add( "version", version );


        return null;
    }
}
