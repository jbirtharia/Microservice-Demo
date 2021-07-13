package com.boot.demo;

import com.boot.demo.dao.FlightFareRepository;
import com.boot.demo.model.FlightFare;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

/**
 * The type Flight fare service application.
 *
 * @author JayendraB  Created on 02/07/21
 */
@SpringBootApplication
public class FlightFareServiceApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(FlightFareServiceApplication.class, args);
	}

	/**
	 * Populate initial data command line runner.
	 *
	 * @param repository the repository
	 * @return the command line runner
	 */
	@Bean
	public CommandLineRunner populateInitialData(FlightFareRepository repository) {
		return args -> {
			FlightFare fare1 = new FlightFare(123L, "S123",
					BigDecimal.valueOf(100),"USD");
			FlightFare fare2 = new FlightFare(124L, "S124",
					BigDecimal.valueOf(110),"USD");
			repository.save(fare1);
			repository.save(fare2);
		};
	}

}
