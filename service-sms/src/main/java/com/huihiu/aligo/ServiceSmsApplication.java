package com.huihiu.aligo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServiceSmsApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger( ServiceSmsApplication.class );

	public static void main(String[] args) {
		SpringApplication.run(ServiceSmsApplication.class, args);
		LOGGER.info( "service-sms 启动成功..." );
	}

}
