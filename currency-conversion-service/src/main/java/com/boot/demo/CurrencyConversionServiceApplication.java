package com.boot.demo;

import com.boot.demo.dao.CurrencyConverterRepository;
import com.boot.demo.model.CurrencyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class CurrencyConversionServiceApplication implements CommandLineRunner {

	@Autowired
	private CurrencyConverterRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		CurrencyConverter converter1 = new CurrencyConverter(1L, "USD",
				"INR", BigDecimal.valueOf(74.18));
		CurrencyConverter converter2 = new CurrencyConverter(2L, "USD",
				"JPY", BigDecimal.valueOf(111.12));

		repository.save(converter1);
		repository.save(converter2);

	}
}
