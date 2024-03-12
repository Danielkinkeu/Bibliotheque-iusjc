package com.isi.trombinoscope;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;



@SpringBootApplication
@EnableEurekaClient
public class TrombinoscopeApplication
//		extends SpringBootServletInitializer
{

//	@Override
//	// Configuring method has to be overridden
//	protected SpringApplicationBuilder
//	configure(SpringApplicationBuilder application)
//	{
//		return application.sources(
//				TrombinoscopeApplication.class);
//	}

	public static void main(String[] args) {
		SpringApplication.run(TrombinoscopeApplication.class, args);
	}

}
