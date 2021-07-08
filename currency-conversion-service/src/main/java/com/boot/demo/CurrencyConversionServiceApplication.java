package com.boot.demo;

import com.boot.demo.dao.CurrencyConverterRepository;
import com.boot.demo.model.CurrencyConverter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;

@SpringBootApplication
@EnableDiscoveryClient
public class CurrencyConversionServiceApplication{

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner populateInitialData(CurrencyConverterRepository repository) {
		return args -> {
			CurrencyConverter converter1 = new CurrencyConverter(1L, "USD",
					"INR", BigDecimal.valueOf(74.18),null);
			CurrencyConverter converter2 = new CurrencyConverter(2L, "USD",
					"JPY", BigDecimal.valueOf(111.12),null);

			repository.save(converter1);
			repository.save(converter2);
		};
	}
}
