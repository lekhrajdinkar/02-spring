## Secure REST
- REST APIs are stateless, they should not use sessions or cookies, use JWT which is also Stateless.
- HTTPS: Securing data in transit using `SSL/TLS`.
- Deprecated :: `WebSecurityConfigurerAdapter`.
  - No need to implement it.
  - new approach is functional Style.
    - Bean:`SecurityFilterChain` - Configure HTTP security,specifying which requests are authorized,etc
    - Bean:`WebSecurityCustomizer/FI` - ignore specific paths from Spring Security, etc
    

## Options
### 1. basic/digest Authentication
 > com.lekhraj.java.spring.SB_99_RESTful_API.configuration.Security_01_Config
- AuthenticationEntryPoint - Configure it differently for basic and digest.

### 2. API Keys
- https://www.baeldung.com/spring-boot-api-key-secret
- Some REST APIs use API keys for authentication.
- An API-key is like `token`, that identifies the - `API-client to the API without referencing an actual user`.
- API-key can be sent in the queryString/header.
- it’s possible to hide the key using SSL.
- Create `Custom Filter` to Check API-Check
- eg: CCGG MuleSoft API


### 3. OAuth 2.0 JWT / Authorization (Preferred)
 - OAuth2 : https://dev-16206041-admin.okta.com/admin/apps/active
 - auth0 : https://manage.auth0.com/dashboard/us/dev-gpg8k3i38lkcqtkw/onboarding 
   - signed up with Github
   - dev-gpg8k3i38lkcqtkw


---
## Extra
1. Security headers :
- `Strict-Transport-Security` 
- `X-Content-Type-Options`
- `X-Frame-Options`
- `Content-Security-Policy`