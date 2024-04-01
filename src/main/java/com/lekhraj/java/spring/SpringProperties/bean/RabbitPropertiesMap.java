package com.lekhraj.java.spring.SpringProperties.bean;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@ToString
@Component
public class RabbitPropertiesMap{
    @Value("${rabbit.mq.url}") String url;
    @Value("${rabbit.mq.username}") String username;
    @Value("${rabbit.mq.password}") String password;
}