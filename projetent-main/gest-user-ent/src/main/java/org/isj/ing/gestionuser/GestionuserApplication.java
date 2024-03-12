package org.isj.ing.gestionuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableEurekaClient
@ComponentScan({"org.isj.ing.gestionuser.*"})
public class GestionuserApplication extends SpringBootServletInitializer {

	@Override
	// Configuring method has to be overridden
	protected SpringApplicationBuilder
	configure(SpringApplicationBuilder application)
	{
		return application.sources(
				GestionuserApplication.class);
	}

	public static void main(String[] args) {
		System.out.println((new BCryptPasswordEncoder()).encode("ent2022"));
		//System.out.println((new Passwo));
		SpringApplication.run(GestionuserApplication.class, args);
	}

}
