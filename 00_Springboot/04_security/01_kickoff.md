- https://chatgpt.com/c/5865254e-a777-416f-ad16-8e40df050c04 - LDAP
- https://chatgpt.com/c/93986452-c257-4324-859b-6e08142fb677 - spring security overview

---
# SpringBoot Security
- JWT - Sateless Authorization/Authorization
- Authentication and access-control framework.
- Enable:
  - Add :<artifactId>spring-boot-starter-security</artifactId>
  - @config @EnableWebSecurity webSecurityConfig { @Bean:`SecurityFilterChain`, ... }
- fully disable the security.
  - @SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
  - spring.autoconfigure.exclude = org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration

---
```
 CCGG pattern/s
   - App <--> Authenticating users against an LDAP directory, directly. (old)
   - App <--> OKTA <--> LDAP Authentication
   - Using SAML for single sign-on (SSO) with external identity providers/Okta.
   - SSL
   
```

> com.lekhraj.java.spring.SB_99_RESTful_API.configuration.Security_01_Config
---

## Spring : Authentication
- key term : 
  - `UserDetail` (name,pass,role,etc), 
  - `UserDetailsService`
  - `Authentication`
  - `AuthenticationManager` and `AuthenticationManagerBuilder`
    - central point for authentication logic
    - `AuthenticationProviders`  
- Custom beans :  UserDetailsService AuthenticationProvider, Filters

1. `Form-based Authentication` (not for REST api) // http.loginForm()...
2. `Basic Authentication` / Digest Authentication (old,hashed credentials)
    - hide credential: Authorization header :: Base64-encoded string username:password.
    - it’s possible to hide the key using SSL.
3. `LDAP` - springs helps to integrating with LDAP and perform authentication.
   
4. `OpenID Connect/OAuth2.0` ( PREFERRED )
  - springs helps to integrating with external authentication-providers(okta,google,etc)
  - Identity token generate by Okta, requested by UI or consumer.
  - okta:
    - Multi-factor Authentication: configuring it
    - `SpringApp <--> okta <--> LDAP Integration`, for Authentication

---

## Spring : Authorization
1. `Role-based Access Control (RBAC)`
   - Assigning roles to users and granting access based on those roles.
   - program : pending
2. `Method-level Security` : https://www.baeldung.com/spring-security-method-security
   - @PreAuthorize 
   - @PostAuthorize 
   - @Secured 
   - @RolesAllowed
3. `URL-based Security`
 - Restricting access to web resources based on URL patterns.

4. `OAuth2.0` ( PREFERRED )
 - springs helps to integrating with external authentication-providers(okta,google,etc)
 - Access Token generate by Okta ; requested by UI.
 - `Spring <--> okta <--> LDAP Integration`, for Authorization
 - parsing and validating JWT tokens.

---
## More pointers // explore these.
1. Cross-Origin Resource Sharing (CORS) settings
2. Password Management : AWS-secret-manager, Encoding, CyberArk, etc
3. Security Custom Filters <pending>
4. Prevent XSS and CSRF
   -  CSRF : enabled by default, 
   -  create custom filter for further CSRF protection
5. Logging and Monitoring
6.` @EnableGlobalMethodSecurity(prePostEnabled = true)`
   - `@PreAuthorize`("hasAuthority('SCOPE_my.spring.app.scope')")
   - 
 
---
## Extra
- Bean : WebSecurityCustomizer
- SAML Authentication: Configuring SAML-based authentication.



