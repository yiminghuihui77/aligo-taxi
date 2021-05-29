package com.huihui.aligo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class CloudZuulApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger( CloudZuulApplication.class );

	public static void main(String[] args) {
		SpringApplication.run(CloudZuulApplication.class, args);
		LOGGER.info( "cloud-zuul 启动成功..." );
	}

}
