package com.lekhraj.java.spring.SB_99_RESTful_API.controller;

import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> m3() {
          ResponseEntity httpResponse2 = ResponseEntity
                .status(302)
                .header("location", "https://auth0.com/intro-to-iam/what-is-oauth-2")
                .body("redirect");
        return httpResponse2;
    }
}

// Role : Admin, user
