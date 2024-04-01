package com.lekhraj.java.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication
@PropertySources(value = {
		@PropertySource(value = "classpath:database-{spring.profiles.active}.properties"),
		@PropertySource(value = "classpath:rabbit-mq-{spring.profiles.active}.properties")
})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
