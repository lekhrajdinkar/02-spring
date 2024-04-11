package com.lekhraj.java.spring.SpringRest.controller;

import com.lekhraj.java.spring.SpringRest.model.dto.CoffeeDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class CoffeeController {

    @GetMapping("/api/get-sample-coffee")
    public CoffeeDTO getSampleCoffee(){
        //return CoffeeDTO.builder().name("Lek-Coffee").size(2).createTime(LocalDateTime.now()).build();
        return CoffeeDTO.builder().name(null).size(2).createTime(LocalDateTime.now()).build();
    }

}
