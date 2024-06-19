package com.lekhraj.java.spring.SB_99_RESTful_API.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
public class SecurityController
{
    @GetMapping("/admin/secured-api-1")
    public String m1() { return "ADMIN :: secured-api-1";}

    @GetMapping("/user/secured-api-2")
    public String m2() { return "USER :: secured-api-2";}

    @GetMapping("/no-auth-api")
    public String m3() { return "no-auth-api";}
}

// Role : Admin, user
