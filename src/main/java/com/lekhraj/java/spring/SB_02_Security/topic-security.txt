== Web Security ===
<artifactId>spring-boot-starter-security</artifactId> + @EnableWebSecurity

fully disable the security.
    - @SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
    - spring.autoconfigure.exclude = org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration

Beans : InMemoryUserDetailsManager, SecurityFilterChain, PasswordEncoder

OAuth2


