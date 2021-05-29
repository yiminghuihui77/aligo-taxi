package com.huihui.aligo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ServicePassengerUserApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger( ServicePassengerUserApplication.class );
	public static void main(String[] args) {
		SpringApplication.run(ServicePassengerUserApplication.class, args);
		LOGGER.info( "service-passenger-user 启动成功..." );
	}

}
