package com.lekhraj.java.spring.Spring_03_Properties.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "server") // from yml file. good choice for hiriecal.
// we tell Spring to map all the properties with the specified prefix to an object of Prop2Map.

// Also, register it as bean :
// - @Component, or
// - @EnableConfigurationProperties(Prop2Map.class)

// Note: Explicitly register in Application.java

public class Prop2Map
{

    private Map<String, String> application;
    private Map<String, List<String>> config;
    private Map<String, Credentials> users;
}

/*
@Value
allows us to directly inject a particular property value by its key.

@ConfigurationProperties
annotation binds multiple properties to a particular object (ReferenceType,Collection,Map)+PrimitiveType.,
 */
