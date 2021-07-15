package com.boot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * The type Flight api gateway application.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class FlightApiGatewayApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(FlightApiGatewayApplication.class, args);
	}

}
