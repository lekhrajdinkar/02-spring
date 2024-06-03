package com.lekhraj.java.spring.SB_99_RESTful_API.controller;

import com.lekhraj.java.spring.SB_99_RESTful_API.model.dto.JewelleryDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("api/v1/jewellery")
@Tag(name = "Jewellery API for My-Store", description = "Custom API for demonstrating Jewellery APIs")
public class JewelleryController {

    @GetMapping(value={"get-one"})
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
    // Optional Path Variable
    @GetMapping(value = {
             "get-one-2/{pathVariable1}"
            ,"get-one-2/{pathVariable1}/{pathVariable2}"
    })
    ResponseEntity<JewelleryDTO> getJewelleryRE(
            HttpServletResponse response,
            //@RequestBody JewelleryDTO body,
            @PathVariable("pathVariable1") String pathVariable1,
            @PathVariable(value="pathVariable2", required = false) String pathVariable2_optional,
            @PathVariable Map<String,String> allPathVariables,

            @RequestHeader("h1") String h1,
            @RequestHeader(value="h2",required = false, defaultValue = "v2-default") String h2_Optional,
            @RequestHeader Map<String,String> allHeader,
            @RequestHeader HttpHeaders httpHeaders,

            @RequestParam(value = "ReqParam1", defaultValue = "defaultValue", required = false) String reqParam1,
            @RequestParam Map<String,String> allRequestParams
    ) throws Exception
    {
        JewelleryDTO dto =  JewelleryDTO.builder()
                .name("Jewellery-2")
                .id(2)
                .price(200)
                .createTime(LocalDateTime.now())
                .build();

        log.info(" \n\n >>> pathVariable1|2 : {}|{}, header-1|2: {}|{} \n", pathVariable1,pathVariable2_optional, h1,h2_Optional);
        allHeader.forEach((k,v)-> System.out.println(k+":"+v));
        allPathVariables.forEach((k,v)-> System.out.println(k+"::"+v));
        allRequestParams.forEach((k,v)-> System.out.println(k+":::"+v));

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
