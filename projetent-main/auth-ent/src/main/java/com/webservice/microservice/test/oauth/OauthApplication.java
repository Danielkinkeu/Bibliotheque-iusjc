package com.webservice.microservice.test.oauth;
import com.webservice.microservice.test.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableEurekaClient
public class OauthApplication extends SpringBootServletInitializer {

	@Override
	// Configuring method has to be overridden
	protected SpringApplicationBuilder
	configure(SpringApplicationBuilder application)
	{
		return application.sources(
				OauthApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(OauthApplication.class, args);
	}

}
