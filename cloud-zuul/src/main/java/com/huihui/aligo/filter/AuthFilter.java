package com.huihui.aligo.filter;

import com.huihui.aligo.constant.RedisKeyPrefixConstant;
import com.huihui.aligo.util.JwtInfo;
import com.huihui.aligo.util.JwtUtil;
import com.huihui.aligo.util.RedisUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 登录token认证过滤器
 *
 * @author minghui.y
 * @create 2021-05-29 3:36 下午
 **/
@Component
@Slf4j
public class AuthFilter extends ZuulFilter {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * filterType:pre、route、post、error
     * @return
     */
    @Override
    public String filterType() {
        //前置过滤器
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        //过滤器的顺序（数字越小越先执行）
        return 0;
    }

    /**
     * 返回值为true才执行
     * @return
     */
    @Override
    public boolean shouldFilter() {
        //可以拦截指定url
        return true;
    }


    /**
     * 具体的过滤器逻辑
     * return null表示放行
     * @return
     * @throws ZuulException
     */
    @Override
    public Object run() throws ZuulException {
        //获取请求
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();

        log.info( String.format( "uri: %s >>> method: %s",
                request.getRequestURL().toString(), request.getMethod() ) );

        //请求头中获取token
        String token = request.getHeader("Authorization");
//        String token = request.getParameter( "token" );
        if (!StringUtils.isEmpty( token )) {
            JwtInfo tokenJwtInfo = JwtUtil.parseToken(token);

            if(null != tokenJwtInfo) {
                String tokenUserId = tokenJwtInfo.getSubject();
                Long tokenIssueDate = tokenJwtInfo.getIssueDate();

                String tokenInRedis = (String) redisUtil.get( RedisKeyPrefixConstant.PASSENGER_LOGIN_TOKEN_APP_KEY_PRE + tokenUserId );
                log.info( "header中的token:{}", token );
                log.info( "redis中的token:{}", tokenInRedis );

                if (token.equals(tokenInRedis)){
                    //token验证通过，放行
                    return null;
                }
            }
        }


        //继续执行后续过滤器，但是不向后面的服务转发
        requestContext.setSendZuulResponse( false );
        requestContext.setResponseStatusCode( HttpStatus.UNAUTHORIZED.value() );
        requestContext.setResponseBody( "auth failed!" );
        return null;
    }
}
