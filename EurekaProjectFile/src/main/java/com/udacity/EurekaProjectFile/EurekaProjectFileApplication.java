package com.udacity.EurekaProjectFile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaProjectFileApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaProjectFileApplication.class, args);
	}

}
