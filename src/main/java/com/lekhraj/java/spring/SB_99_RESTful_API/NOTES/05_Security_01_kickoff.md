## Spring Security
- Enable:
  - <artifactId>spring-boot-starter-security</artifactId> 
  - @config @EnableWebSecurity webSecurityConfig { bean:`SecurityFilterChain`, ... }
- fully disable the security.
    - @SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
    - spring.autoconfigure.exclude = org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration
- OAuth2 : https://dev-16206041-admin.okta.com/admin/apps/active

---
## Core
- UserDetailsManager : InMemoryUserDetailsManager
- Security_01_SecurityFilterChain : https://chatgpt.com/c/af6089f9-a7e9-4dac-9f28-b259200a7167
- SecurityFilterChain = HttpSecurity http......build(); 
  - `authorizeRequests()`
  - formLogin()  
  - logout()
  - csrf()
  - httpBasic()
  - sessionManagement()
  - addFilterAfter()

## Secure REST
### Authentication 
1. formLogin
- Not recommended.
- API rely on token-based authentication mechanisms such as JWT, rather than session-based authentication.

2. basic Authentication


