package com.king.Bibliotheque;

import com.king.Bibliotheque.Models.Loan;
import com.king.Bibliotheque.Models.Reservation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackageClasses = {Loan.class, Reservation.class})

public class BibliothequeApplication {
	public static void main(String[] args) {
		SpringApplication.run(BibliothequeApplication.class, args);
	}
}
