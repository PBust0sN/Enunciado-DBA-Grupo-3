package com.example.ClimateChangeBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ClimateChangeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClimateChangeBackendApplication.class, args);
	}

}
