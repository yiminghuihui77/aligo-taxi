package com.huihui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
//@RibbonClient(name = "service-verification-code", configuration = RibbonConfig.class)
public class ApiPassengerApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger( ApiPassengerApplication.class );
	public static void main(String[] args) {
		SpringApplication.run(ApiPassengerApplication.class, args);
		LOGGER.info( "Api-passenger 启动成功..." );
	}


	/*@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}*/

}
