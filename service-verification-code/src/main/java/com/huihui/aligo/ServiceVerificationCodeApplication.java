package com.huihui.aligo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
//启用feign客户端 （发起方）
@EnableFeignClients
public class ServiceVerificationCodeApplication {

	private static final Logger LOGGER= LoggerFactory.getLogger( ServiceVerificationCodeApplication.class );

	public static void main(String[] args) {
		SpringApplication.run(ServiceVerificationCodeApplication.class, args);
		LOGGER.info( "Service-verification-code 启动成功..." );
	}

}
