package com.lekhraj.java.spring.SpringProperties.bean;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ToString
@Component
@Data
@ConfigurationProperties(prefix = "db") // <<<<
public class DatabasePropertiesMap{

    // keep property name matching with prop file  <<<
    // Notice : getter/setter must present.
    String url;  //db.url
    String username; //db.username
    String password;//db.username

    @Value("${db.url}") String url2;
    @Value("${db.username}") String username2;
    @Value("${db.password}") String password2;
}

/*
java.lang.IllegalArgumentException: Could not resolve placeholder 'db.url' in value "${db.url}"

BeanCreationException: Error creating bean with name 'databasePropertiesMap': Injection of autowired dependencies failed

springframework.beans.factory.UnsatisfiedDependencyException:
Error creating bean with name 'runner_2': Unsatisfied dependency expressed through field 'dbmap':
 */
