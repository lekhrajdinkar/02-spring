package com.lekhraj.java.spring.SpringRest.model.dto;

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
