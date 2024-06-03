package com.lekhraj.java.spring.SB_99_RESTful_API.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebMvc
//@ComponentScan(basePackages = "com.lekhraj.java.spring.SB_99_RESTful_API.configuration.controller")
public class WebConfig {

    @Bean
    WebMvcConfigurer webMvcConfigurerForApp(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*");
            }
        };
    }
}
