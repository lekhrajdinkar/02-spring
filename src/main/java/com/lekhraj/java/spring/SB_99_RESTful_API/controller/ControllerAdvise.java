package com.lekhraj.java.spring.SB_99_RESTful_API.controller;

// Global Error handling

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

//@RestControllerAdvice //@ControllerAdvice
public class ControllerAdvise {
    //@ExceptionHandler(Exception.class)
    ResponseEntity<String> handle(WebRequest request){
        return new ResponseEntity<>(HttpStatusCode.valueOf(400));
    }
}
