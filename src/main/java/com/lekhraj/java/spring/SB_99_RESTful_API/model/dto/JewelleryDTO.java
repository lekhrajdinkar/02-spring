package com.lekhraj.java.spring.SB_99_RESTful_API.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@ToString
@Builder
@Setter
@Getter

public class JewelleryDTO
{
    // Bean/dto Validation
    // @NameCheckAnnotation
    @JsonProperty("jewelleryName")
    @NotBlank(message = "Name not given")
    String name;

    @JsonProperty("jewelleryId")
    @NotBlank(message = "Id not given")
    int id;

    @JsonProperty("jewelleryPrice")
    @Size(min = 10, max = 1000, message = "Must be between 100 and 1000")
    double price;

    @DateTimeFormat
    LocalDateTime createTime;
}
