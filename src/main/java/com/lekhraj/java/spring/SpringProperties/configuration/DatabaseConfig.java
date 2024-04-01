package com.lekhraj.java.spring.SpringProperties.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

// database-dev1/2.property

@Configuration
@PropertySource(value = "classpath:database-{spring.profiles.active}.properties")
public class DatabaseConfig {
}
