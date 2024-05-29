package com.lekhraj.java.spring.SB_99_RESTful_API.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@ToString
@Builder
@Setter
@Getter
public class CoffeeDTO {
    String name;
    int size;
    LocalDateTime createTime;
}
