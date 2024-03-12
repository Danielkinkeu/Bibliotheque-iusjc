package org.isj.ing3.isi.webservice.webservicerest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;



@SpringBootApplication
@EnableEurekaClient
//@ComponentScan("package org.isj.ing3.isi.webservice.webservicerest.*")
public class WebservicerestApplication   extends SpringBootServletInitializer {

	@Override
	// Configuring method has to be overridden
	protected SpringApplicationBuilder
	configure(SpringApplicationBuilder application)
	{
		return application.sources(
				WebservicerestApplication.class);
	}

	//@Override

	// Configuring method has to be overridden
	/*protected SpringApplicationBuilder
	configure(SpringApplicationBuilder application)
	{
		return application.sources(
				WebservicerestApplication.class);
	}*/
	/*@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL).addScript("")
	}*/

	public static void main(String[] args) {


		SpringApplication.run(WebservicerestApplication.class, args);
	}

}
