package com.lekhraj.java.spring.SpringProperties.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Builder
public class Credentials {
    private String username;
    private String password;
}
