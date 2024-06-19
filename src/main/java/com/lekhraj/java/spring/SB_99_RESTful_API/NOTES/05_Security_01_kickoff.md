## Spring Security
- <artifactId>spring-boot-starter-security</artifactId> + @EnableWebSecurity
- fully disable the security.
    - @SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
    - spring.autoconfigure.exclude = org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration

Beans : InMemoryUserDetailsManager, SecurityFilterChain, PasswordEncoder

OAuth2
- https://dev-16206041-admin.okta.com/admin/apps/active

---

UserDetailsManager - InMemoryUserDetailsManager
SecurityFilterChain 


