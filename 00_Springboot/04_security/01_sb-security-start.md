- spring security overview :https://chatgpt.com/c/93986452-c257-4324-859b-6e08142fb677
---
## concepts
- LDAP : https://chatgpt.com/c/5865254e-a777-416f-ad16-8e40df050c04 
  - DN entries
- Authentication vs Authorization
- token - JWT https://jwt.io/introduction/
- web filter
- CCGG pattern/s
  - App <--> Authenticating users against an LDAP directory, **directly**. (old)
  - App <--> **OKTA** <--integrated--> LDAP Authentication
    - okta has user Authentication rule configured with LDAP
    - okta has user access config. eg: which **scope** can ask. 
    - okta has **MFA** enabled
    - developer dont need to much on application level.
  - Using SAML for single sign-on (SSO) with external identity providers/Okta.
- security on the fly : TLS/SSL
---
## Spring Boot Security
### Intro
- Authentication and access-control framework.
- use web-filter bts
- `Disable` auto-config
    - @SpringBootApplication(exclude = { **SecurityAutoConfiguration.class** })
    - spring.autoconfigure.exclude = org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration
- `Enable` auto-config
  - Add **spring-boot-starter-security**
  - just need to add/register our webfilter beam : @Bean **SecurityFilterChain**
  ```
  @Configuration
  public class SecurityConfig 
  {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests(authorize -> authorize
                .antMatchers("/path-read").hasAuthority("SCOPE_ScopeRead")    
                .antMatchers("/path-write").hasAuthority("SCOPE_ScopeWrite") 
                .anyRequest().authenticated()                         
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt() // Validate JWT tokens
            );
        return http.build();
    }
   }
  ```
- **claims** (payload in JWT)
    ```
    {
    "sub": "1234567890",
    "name": "Lekhraj Dinkar",
    "roles": ["USER_ADMIN"],                               <<<
    "scp": ["ScopeRead", "ScopeWrite"],                    <<<
    "iat": 1689704000,
    "exp": 1689707600
    }
    ```
  - in SpEL, refer them like
    - **SCOPE_** ScopeRead
    - **ROLE_** USER_ADMIN
---





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



