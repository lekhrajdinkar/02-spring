package com.lekhraj.java.spring.SpringProperties.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

// Read : (Notice Global)
// - global-database-dev1/2.property
// - global-rabbit-mq-dev1/2.property

@Configuration  // un-comment/Comment  <<<<
@PropertySources(value = {
        @PropertySource(value = "classpath:global-database-${spring.profiles.active}.properties"),
        @PropertySource(value = "classpath:global-rabbit-mq-${spring.profiles.active}.properties") // order matter <<<
})
public class ReadConfigFromGlobalProperty {
}
