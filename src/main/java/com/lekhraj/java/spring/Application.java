package com.lekhraj.java.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.out.println("=== Spring Core ===");
		SpringApplication.run(Application.class, args);

	}

}
