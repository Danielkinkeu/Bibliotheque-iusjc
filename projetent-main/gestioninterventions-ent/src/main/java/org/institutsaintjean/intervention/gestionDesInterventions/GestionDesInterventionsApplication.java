package org.institutsaintjean.intervention.gestionDesInterventions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableEurekaClient
@ComponentScan(basePackages = "org.institutsaintjean.intervention.gestionDesInterventions")
public class GestionDesInterventionsApplication extends SpringBootServletInitializer {

	@Override
	// Configuring method has to be overridden
	protected SpringApplicationBuilder
	configure(SpringApplicationBuilder application)
	{
		return application.sources(
				GestionDesInterventionsApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(GestionDesInterventionsApplication.class, args);
	}

}
