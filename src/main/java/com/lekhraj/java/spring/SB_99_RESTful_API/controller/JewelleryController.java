package com.lekhraj.java.spring.SB_99_RESTful_API.controller;

import com.lekhraj.java.spring.SB_99_RESTful_API.model.dto.JewelleryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/jewellery")
@Tag(name = "Jewellery API for My-Store", description = "Custom API for demonstrating Jewellery APIs")
public class JewelleryController {

    @GetMapping("get-one")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody

    @Operation(
            summary = "Get random jewellery",
            description = "Gives hardcoded object",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    schema = @Schema(implementation = JewelleryDTO.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Item not found")
            }
    )
    JewelleryDTO getJewellery() throws Exception
    {
        JewelleryDTO dto =  JewelleryDTO.builder()
                .name("Jewellery-1")
                .id(1)
                .price(20000)
                .createTime(LocalDateTime.now())
                .build();
        //throw new Exception("testing-exception");     <<<<
        return dto;
    }

    /**
     * @return : new ResponseEntity<JewelleryDTO>() - 3 constructor             <<<<
     * @throws Exception
     */
    @GetMapping("get-one-2")
    ResponseEntity<JewelleryDTO> getJewelleryRE( HttpServletResponse response) throws Exception
    {
        JewelleryDTO dto =  JewelleryDTO.builder()
                .name("Jewellery-2")
                .id(2)
                .price(200)
                .createTime(LocalDateTime.now())
                .build();

        // Way-1
        // response.setHeader("h1","v1");
        // response.setStatus(200);

        // way-2
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("h1","v1");
        ResponseEntity httpResponse1 = new ResponseEntity<JewelleryDTO>(dto,responseHeader,HttpStatus.OK);

        // WAY-3 ResponseEntity BUILDER
        ResponseEntity httpResponse2 = ResponseEntity
                .status(200)
                .header("h1", "v1")
                .header("h2", "v2")
                .body(dto);

        return httpResponse2;
    }
}
