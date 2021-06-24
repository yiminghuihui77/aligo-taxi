package com.huihui.aligo.rule;

import com.huihui.aligo.threadlocal.ParameterThreadLocal;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 灰度路由规则
 *
 * @author minghui.y
 * @create 2021-06-24 11:29 上午
 **/
public class GrayRule extends AbstractLoadBalancerRule {

    @Autowired
    private ParameterThreadLocal parameterThreadLocal;




    @Override
    public void initWithNiwsConfig( IClientConfig iClientConfig ) {

    }

    /**
     *
     * @param o
     * @return 返回的Server就是目标服务实例
     */
    @Override
    public Server choose( Object o ) {

        System.out.println("ooo");


        return null;
    }


    public Server choose( Object key, ILoadBalancer loadBalancer ) {

        //获取所有可达服务
        List<Server> reachableServers = loadBalancer.getReachableServers();

        //获取当前线程上下文中的 version
        String version = StringUtils.EMPTY;
        Map<String, String> paramMap = parameterThreadLocal.get();
        if (paramMap != null && StringUtils.isNotBlank( paramMap.get( "version" ) )) {
            version = paramMap.get( "version" );
        }

        //遍历可达服务，获取medata中的version
        for (Server o : reachableServers) {
            //类型转换
            DiscoveryEnabledServer targetServer = (DiscoveryEnabledServer) o;

            //服务实例的medata-map数据
            Map<String, String> metadata = targetServer.getInstanceInfo().getMetadata();
            String instanceVersion = metadata.get( "version" );
            if (version.equals( instanceVersion )) {
                return o;
            }
        }

        return null;
    }




}
