package com.boot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * The type Cloud configuration server application.
 *
 * @author JayendraB  Created on 02/07/21
 */
@SpringBootApplication
@EnableConfigServer
public class CloudConfigurationServerApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(CloudConfigurationServerApplication.class, args);
	}

}
