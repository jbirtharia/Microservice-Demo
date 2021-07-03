package com.boot.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CloudConfigurationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudConfigurationServerApplication.class, args);
	}

}
