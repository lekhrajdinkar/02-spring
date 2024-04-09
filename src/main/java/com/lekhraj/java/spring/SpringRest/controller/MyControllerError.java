package com.lekhraj.java.spring.SpringRest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class MyControllerError implements ErrorController {
    @Autowired
    ErrorAttributes errors; // <<< inject

    @RequestMapping("/error-controller")
    public String handleError(WebRequest req){ // notice web request
        ErrorAttributeOptions options = ErrorAttributeOptions.defaults();
        // ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE, ErrorAttributeOptions.Include.EXCEPTION));
        this.errors.getError(req);
        this.errors.getErrorAttributes(req,options);
        return "Error occured";
    }

     //@RequestMapping("/error/404")
     //public void handleError404(WebRequest req){}

     //@RequestMapping("/error/403")
     //public void handleError403(WebRequest req){}
}

// BasicErrorController --> /error --> Already done.
// server.error.path=/error

// In summary, server.error.404 takes precedence over
// server.error.path for handling specific HTTP error codes like 404.
