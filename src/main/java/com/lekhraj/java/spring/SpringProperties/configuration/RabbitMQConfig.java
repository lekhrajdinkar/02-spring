package com.lekhraj.java.spring.SpringProperties.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

// rabbit-mq-dev1/2.property

@Configuration
@PropertySource(value = "classpath:rabbit-mq-{spring.profiles.active}.properties")
public class RabbitMQConfig {
}
