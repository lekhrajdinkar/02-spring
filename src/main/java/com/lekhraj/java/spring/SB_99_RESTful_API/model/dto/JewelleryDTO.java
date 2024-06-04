package com.lekhraj.java.spring.SB_99_RESTful_API.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@ToString
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JewelleryDTO
{
    // Bean/dto Validation
    // @NameCheckAnnotation
    @JsonProperty("jewelleryName")
    @NotBlank(message = "Name not given")
    String name;

    @JsonProperty("jewelleryId")
    @Min(value = 1, message ="Id missing")
    int id;

    @JsonProperty("jewelleryPrice")
    //@Size(min = 10, max = 1000, message = "Must be between 100 and 1000")
    @Max(value = 1000, message = "maximum value is 1000.")
    double price;

    @DateTimeFormat
    LocalDateTime createTime;
}
