package com.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HealthInsuranceSystemApplication {

	public static void main(String[] args) {
		System.out.println("Main start");
		ConfigurableApplicationContext run = SpringApplication.run(HealthInsuranceSystemApplication.class, args);
		System.out.println("Main End");
		System.out.println("Closing run");
		run.close();
	}

}
