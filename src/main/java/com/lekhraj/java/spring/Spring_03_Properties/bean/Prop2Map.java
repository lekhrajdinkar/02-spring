package com.lekhraj.java.spring.Spring_03_Properties.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "server") // from yml file.
// @Configuration
public class Prop2Map // Note: Explicitly register in Application.java
{
    private Map<String, String> application;
    private Map<String, List<String>> config;
    private Map<String, Credentials> users;

    /*
    @ConfigurationProperties(prefix = "server")
    @Bean
    Prop2Map getProp2Map(){
        return new Prop2Map();
    }
    */
}

/*
@Value
allows us to directly inject a particular property value by its key.

    vs

@ConfigurationProperties
annotation binds multiple properties to a particular object (ReferenceType,Collection,Map).
 */


