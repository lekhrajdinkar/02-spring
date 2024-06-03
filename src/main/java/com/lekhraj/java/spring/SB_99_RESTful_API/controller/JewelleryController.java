package com.lekhraj.java.spring.SB_99_RESTful_API.controller;

import com.lekhraj.java.spring.SB_99_RESTful_API.model.dto.JewelleryDTO;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/jewellery")
public class JewelleryController {

    @GetMapping("get-one")
    public JewelleryDTO get() throws Exception
    {
        JewelleryDTO dto =  JewelleryDTO.builder()
                .name("Jewellery-1")
                .id(1)
                .price(20000)
                .createTime(LocalDateTime.now())
                .build();
        //throw new Exception("testing-exception");
        return dto;
    }

    @PostMapping("get-one-2")
    public JewelleryDTO get2(@RequestBody JewelleryDTO dto) throws Exception
    {
        System.out.println("-- No Binding Error --");
        return dto;
    }


}
