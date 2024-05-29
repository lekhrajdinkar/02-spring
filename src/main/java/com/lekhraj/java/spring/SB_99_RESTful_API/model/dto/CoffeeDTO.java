package com.lekhraj.java.spring.SB_99_RESTful_API.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Builder
@Setter
@Getter
public class CoffeeDTO
{
    // Bean/dto Validation
    @NotBlank(message = "Coffee name not given")
    String name;

    @Size(min = 1, max = 3, message = "Must be 1(SMALL), 2(MEDIUM) or 3(LARGE)")
    int size;

    LocalDateTime createTime;
}
