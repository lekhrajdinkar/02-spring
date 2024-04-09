package com.lekhraj.java.spring.SpringProperties.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "server")
// we tell Spring to map all the properties with the specified prefix to an object of Prop2Map.
// Also, make this class as bean :
// - @Component, or
// - @EnableConfigurationProperties(Prop2Map.class)
public class Prop2Map
{
    private Map<String, String> application;
    private Map<String, List<String>> config;
    private Map<String, Credential> users;

    @Getter
    @Setter
    public static class Credential {
        private String username;
        private String password;
    }
}

/*
@Value
allows us to directly inject a particular property value by its key.

@ConfigurationProperties
annotation binds multiple properties to a particular object,
and provides access to the properties through the mapped object.
 */
