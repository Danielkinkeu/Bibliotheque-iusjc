package com.webservice.microservice.test.gateway.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient

public class ConfigserverApplication  extends SpringBootServletInitializer {

	@Override
	// Configuring method has to be overridden
	protected SpringApplicationBuilder
	configure(SpringApplicationBuilder application)
	{
		return application.sources(
				ConfigserverApplication.class);
	}
	public static void main(String[] args) {
		SpringApplication.run(ConfigserverApplication.class, args);
	}

}
