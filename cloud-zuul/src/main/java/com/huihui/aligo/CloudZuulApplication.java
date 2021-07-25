package com.huihui.aligo;

import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.huihui.aligo.filter.SentinelRateFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@ServletComponentScan
public class CloudZuulApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger( CloudZuulApplication.class );

	static {
        LOGGER.info( "初始化sentinel限流规则..." );
		List<FlowRule> flowRules = new ArrayList<>();
		FlowRule rule = new FlowRule();
		//目标资源
		rule.setResource( SentinelRateFilter.SENTINEL_PROTECT_RESOURCE );
		//限流类型
		rule.setGrade( RuleConstant.FLOW_GRADE_QPS );
		rule.setCount( 2 );
		flowRules.add( rule );
		FlowRuleManager.loadRules( flowRules );
	}

	public static void main(String[] args) {
		SpringApplication.run(CloudZuulApplication.class, args);
		LOGGER.info( "cloud-zuul 启动成功..." );
	}



}
